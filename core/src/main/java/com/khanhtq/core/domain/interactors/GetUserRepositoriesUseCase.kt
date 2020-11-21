package com.khanhtq.core.domain.interactors

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.gateway.UserRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class GetUserRepositoriesUseCase @Inject constructor(
    private val userRepository: UserRepository
) : UseCase<String, List<@JvmSuppressWildcards RepoEntity>> {
    @ExperimentalCoroutinesApi
    override fun run(param: String): LiveData<Resource<List<RepoEntity>>> = liveData {
        userRepository
            .repos(param)
            .onStart { emit(Resource.loading()) }
            .catch { emit(Resource.error(it.message ?: "Unknown Error")) }
            .collect { emit(it) }
    }
}