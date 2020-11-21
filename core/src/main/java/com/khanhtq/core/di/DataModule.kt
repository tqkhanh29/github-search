package com.khanhtq.core.di

import android.content.Context
import androidx.room.Room
import com.khanhtq.core.BuildConfig
import com.khanhtq.core.data.local.GithubDatabase
import com.khanhtq.core.data.local.UserDao
import com.khanhtq.core.data.remote.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideUserService(retrofit: Retrofit): UserService =
        retrofit.create(UserService::class.java)

    @Provides
    fun provideLocalDatabase(@ApplicationContext applicationContext: Context): GithubDatabase =
        Room.databaseBuilder(
            applicationContext,
            GithubDatabase::class.java, "github"
        ).build()

    @Provides
    fun provideUserDao(githubDatabase: GithubDatabase): UserDao = githubDatabase.userDao()
}