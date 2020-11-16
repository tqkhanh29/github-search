package com.khanhtq.search

import androidx.lifecycle.*
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.domain.interactors.UseCase
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUserUseCase: UseCase<String, List<UserEntity>>
) : ViewModel() {
    val searchQueryLiveData: MutableLiveData<String> = MutableLiveData()
    val searchResultLiveData: LiveData<Resource<List<UserEntity>>> =
        Transformations.switchMap(searchQueryLiveData) { query ->
            liveData {
                searchUserUseCase.run(query)
            }
        }
}