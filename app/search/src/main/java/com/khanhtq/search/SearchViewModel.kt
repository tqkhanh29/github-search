package com.khanhtq.search

import androidx.lifecycle.*
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.domain.interactors.UseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUserUseCase: UseCase<String, List<UserEntity>>
) : ViewModel() {
    val searchQueryLiveData: MutableLiveData<String> = MutableLiveData()
    val searchResultLiveData: LiveData<Resource<List<UserEntity>>> =
        Transformations.switchMap(searchQueryLiveData) { query ->
            liveData {
                searchUserUseCase
                    .run(query)
                    .onStart { emit(Resource.loading()) }
                    .catch { emit(Resource.error(it.message ?: "Unknown Error")) }
                    .collect { emit(it) }
            }
        }
}