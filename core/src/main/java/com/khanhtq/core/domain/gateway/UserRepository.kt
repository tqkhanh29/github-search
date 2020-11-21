package com.khanhtq.core.domain.gateway

import androidx.lifecycle.LiveData
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun search(query: String): Flow<Resource<List<UserEntity>>>

    suspend fun detail(userName: String): Flow<Resource<UserEntity>>

    suspend fun repos(userName: String): Flow<Resource<List<RepoEntity>>>
}