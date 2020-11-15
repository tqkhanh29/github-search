package com.khanhtq.core.data.remote.service

import androidx.lifecycle.LiveData
import com.khanhtq.core.data.model.User
import com.khanhtq.core.data.remote.resp.ApiResponse
import com.khanhtq.core.data.remote.resp.UserSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("search/users")
    fun search(@Query("q") query: String): LiveData<ApiResponse<UserSearchResponse>>

    @GET("user/{username}")
    fun detail(@Path("username") userName: String): LiveData<ApiResponse<User>>

    @GET("users/{owner}/followers")
    fun followers(@Path("owner") owner: String): LiveData<ApiResponse<List<User>>>
}