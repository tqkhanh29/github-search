package com.khanhtq.userdetail

import androidx.lifecycle.ViewModel
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.domain.interactors.UseCase
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val userDetailUseCase: UseCase<String, UserEntity>,
    private val repositoriesUseCase: UseCase<String, List<RepoEntity>>
): ViewModel() {

}