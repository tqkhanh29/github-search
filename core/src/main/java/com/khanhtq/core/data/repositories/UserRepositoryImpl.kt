package com.khanhtq.core.data.repositories

import com.khanhtq.common.state.Resource
import com.khanhtq.common.Executor
import com.khanhtq.core.data.local.UserDao
import com.khanhtq.core.data.model.UserSearchResult
import com.khanhtq.core.data.remote.resp.UserSearchResponse
import com.khanhtq.core.data.remote.service.UserService
import com.khanhtq.core.domain.gateway.UserRepository
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.mapper.toEntity
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userService: UserService,
    private val appExecutor: Executor
) : UserRepository {
    @FlowPreview
    override suspend fun search(query: String): Flow<Resource<List<UserEntity>>> = userDao
        .search(query)
        .flatMapMerge { searchResult ->
            if (searchResult == null) {
                flow { emit(userService.search(query)) }
                    .onEach { resp ->
                        userDao.insertUsers(resp.users)
                        userDao.insertUserSearchResult(
                            UserSearchResult(
                                query,
                                resp.users.map { it.login },
                                resp.totalCount
                            )
                        )
                    }
                    .map { it.users }
            } else {
                userDao
                    .loadUsersByUsernames(searchResult.users)
            }
        }
        .map { users -> users.map { it.toEntity() } }
        .map { if (it.isEmpty()) Resource.error("No data found") else Resource.success(it) }
        .flowOn(appExecutor.io())

    override suspend fun detail(userName: String): Flow<Resource<UserEntity>> =
        flow { emit(userService.detail(userName)) }
            .onEach { newUsr ->
                userDao.insertUser(newUsr)
            }
            .map { Resource.success(it.toEntity()) }
            .flowOn(appExecutor.io())


    override suspend fun repos(userName: String): Flow<Resource<List<RepoEntity>>> = userDao
        .getUserWithRepos(userName)
        .flatMapMerge { repos ->
            if (repos == null || repos.isEmpty()) {
                flow { emit(userService.repos(userName)) }
                    .onEach {
                        userDao.insertRepos(it)
                    }
            } else {
                flow { emit(repos) }
            }
        }
        .map { repos ->
            if (repos.isEmpty())
                Resource.error("No repository found for this user")
            else
                Resource.success(repos.map { it.toEntity() })
        }
        .flowOn(appExecutor.io())
}