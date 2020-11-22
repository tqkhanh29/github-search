package com.khanhtq.core.data.remote.service

import androidx.lifecycle.LiveData
import com.khanhtq.core.data.model.Repo
import com.khanhtq.core.data.model.User
import com.khanhtq.core.data.remote.resp.ApiResponse
import com.khanhtq.core.data.remote.resp.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    suspend fun search(@Query("q") query: String): UserSearchResponse

    @GET("users/{username}")
    suspend fun detail(@Path("username") userName: String): User

    @GET("users/{owner}/repos")
    suspend fun repos(@Path("owner") owner: String): List<Repo>
}