package com.khanhtq.githubsearch.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.khanhtq.common.state.Resource
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.domain.interactors.GetUserDetailUseCase
import com.khanhtq.core.domain.interactors.GetUserRepositoriesUseCase
import com.khanhtq.core.domain.interactors.UseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userDetailUseCase: GetUserDetailUseCase,
    private val repositoriesUseCase: GetUserRepositoriesUseCase
) : ViewModel() {
    private val _userNameLiveData = MutableLiveData<String>()

    @ExperimentalCoroutinesApi
    val userLiveData: LiveData<Resource<UserEntity>> =
        Transformations.switchMap(_userNameLiveData) { userDetailUseCase.run(it) }

    @ExperimentalCoroutinesApi
    val repositoriesLiveData: LiveData<Resource<List<RepoEntity>>> =
        Transformations.switchMap(_userNameLiveData) { repositoriesUseCase.run(it) }

    fun initUsername(userName: String) {
        _userNameLiveData.value = userName
    }
}