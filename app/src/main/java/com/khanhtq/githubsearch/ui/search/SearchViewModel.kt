package com.khanhtq.githubsearch.ui.search

import androidx.lifecycle.*
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.domain.interactors.SearchUserUseCase
import com.khanhtq.core.domain.interactors.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUserUseCase: SearchUserUseCase
) : ViewModel() {
    val searchQueryLiveData: MutableLiveData<String> = MutableLiveData()

    @ExperimentalCoroutinesApi
    val searchResultLiveData: LiveData<Resource<List<UserEntity>>> =
        Transformations.switchMap(searchQueryLiveData) {
            searchUserUseCase.run(it)
        }
}