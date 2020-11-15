package com.khanhtq.core.di

import com.khanhtq.core.BuildConfig
import com.khanhtq.core.data.adapter.LiveDataCallAdapterFactory
import com.khanhtq.core.data.remote.service.UserService
import com.khanhtq.core.data.repositories.UserRepositoryImpl
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity
import com.khanhtq.core.domain.interactors.GetUserDetailUseCase
import com.khanhtq.core.domain.interactors.GetUserRepositoriesUseCase
import com.khanhtq.core.domain.interactors.SearchUserUseCase
import com.khanhtq.core.domain.interactors.UseCase
import com.khanhtq.core.domain.gateway.UserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository


    @Binds
    abstract fun bindSearchUserUseCase(searchUserUseCase: SearchUserUseCase): UseCase<String, List<@JvmSuppressWildcards UserEntity>>

    @Binds
    abstract fun bindUserDetailUseCase(getUserDetailUseCase: GetUserDetailUseCase): UseCase<String, UserEntity>

    @Binds
    abstract fun bindGetUserRepositoriesUseCase(getUserRepositoriesUseCase: GetUserRepositoriesUseCase): UseCase<String, List<@JvmSuppressWildcards RepoEntity>>
}

@Module
@InstallIn(ApplicationComponent::class)
class DataModule {
    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                setLevel(HttpLoggingInterceptor.Level.NONE)
            }
        })
        .build()

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)
}