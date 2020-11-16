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
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userService: UserService,
    private val appExecutor: Executor
) : UserRepository {
    override fun search(query: String): Flow<Resource<List<UserEntity>>> = userDao
        .search(query)
        .flatMapMerge { searchResult ->
            if (searchResult == null) {
                flow<UserSearchResponse> { userService.search(query) }
                    .onEach { resp ->
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
        .map { Resource.success(it) }
        .flowOn(appExecutor.io())

    override fun detail(userName: String): Flow<Resource<UserEntity>> = userDao
        .getUser(userName)
        .map { user ->
            user ?: userService.detail(userName)
        }
        .map { Resource.success(it.toEntity()) }
        .flowOn(appExecutor.io())


    override fun repos(userName: String): Flow<Resource<List<RepoEntity>>> = userDao
        .getUserWithRepos(userName)
        .map { it?.repos ?: userService.repos(userName) }
        .map { repos -> Resource.success(repos.map { it.toEntity() }) }
        .flowOn(appExecutor.io())
}