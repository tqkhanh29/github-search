package com.khanhtq.core.domain.gateway

import androidx.lifecycle.LiveData
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun search(query: String): Flow<Resource<List<UserEntity>>>

    fun detail(userName: String): Flow<Resource<UserEntity>>

    fun repos(userName: String): Flow<Resource<List<RepoEntity>>>
}