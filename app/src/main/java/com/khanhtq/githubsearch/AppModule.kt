package com.khanhtq.githubsearch

import androidx.lifecycle.ViewModelProvider
import com.khanhtq.common.AppExecutor
import com.khanhtq.common.Executor
import com.khanhtq.common.di.ViewModelProviderFactory
import com.khanhtq.core.data.repositories.UserRepositoryImpl
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.domain.gateway.UserRepository
import com.khanhtq.core.domain.interactors.GetUserDetailUseCase
import com.khanhtq.core.domain.interactors.GetUserRepositoriesUseCase
import com.khanhtq.core.domain.interactors.SearchUserUseCase
import com.khanhtq.core.domain.interactors.UseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    abstract fun bindAppExecutor(appExecutor: AppExecutor): Executor

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindUserDetailUseCase(getUserDetailUseCase: GetUserDetailUseCase): UseCase<String, UserEntity>

    @Binds
    abstract fun bindGetUserRepositoriesUseCase(getUserRepositoriesUseCase: GetUserRepositoriesUseCase): UseCase<String, List<@JvmSuppressWildcards RepoEntity>>
}