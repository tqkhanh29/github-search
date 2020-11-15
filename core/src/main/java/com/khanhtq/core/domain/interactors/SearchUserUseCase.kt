package com.khanhtq.core.domain.interactors

import androidx.lifecycle.LiveData
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.domain.gateway.UserRepository
import javax.inject.Inject

class SearchUserUseCase @Inject constructor(
    private val userRepository: UserRepository
): UseCase<String, List<@JvmSuppressWildcards UserEntity>>() {
    override suspend fun run(param: String): LiveData<Resource<List<UserEntity>>> = userRepository.search(param)
}