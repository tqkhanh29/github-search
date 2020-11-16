package com.khanhtq.core.domain.interactors

import androidx.lifecycle.LiveData
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.gateway.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserRepositoriesUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<String, List<@JvmSuppressWildcards RepoEntity>>() {
    override suspend fun run(param: String): Flow<Resource<List<RepoEntity>>> {
        return userRepository.repos(param)
    }
}