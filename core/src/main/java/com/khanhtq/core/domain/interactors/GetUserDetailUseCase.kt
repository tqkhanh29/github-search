package com.khanhtq.core.domain.interactors

import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.domain.gateway.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserDetailUseCase @Inject constructor(
    private val userRepository: UserRepository
): UseCase<String, UserEntity>() {
    override suspend fun run(param: String): Flow<Resource<UserEntity>> {
        return userRepository.detail(param)
    }
}