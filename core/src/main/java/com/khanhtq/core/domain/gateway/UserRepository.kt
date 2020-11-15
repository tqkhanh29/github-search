package com.khanhtq.core.domain.gateway

import androidx.lifecycle.LiveData
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity

interface UserRepository {

    fun search(query: String): LiveData<Resource<List<UserEntity>>>

    fun detail(userName: String): LiveData<Resource<UserEntity>>

    fun repos(userName: String): LiveData<Resource<List<RepoEntity>>>
}