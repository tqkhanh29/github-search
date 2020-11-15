package com.khanhtq.core.data.repositories

import androidx.lifecycle.LiveData
import com.khanhtq.common.state.Resource
import com.khanhtq.core.data.Executor
import com.khanhtq.core.data.local.UserDao
import com.khanhtq.core.data.remote.service.UserService
import com.khanhtq.core.domain.gateway.UserRepository
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val userService: UserService,
    private val appExecutor: Executor
) : UserRepository {
    override fun search(query: String): LiveData<Resource<List<UserEntity>>> {
        TODO("Not yet implemented")
    }

    override fun detail(userName: String): LiveData<Resource<UserEntity>> {
        TODO("Not yet implemented")
    }

    override fun repos(userName: String): LiveData<Resource<List<RepoEntity>>> {
        TODO("Not yet implemented")
    }
}