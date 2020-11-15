package com.khanhtq.core.data.remote.resp

import com.google.gson.annotations.SerializedName
import com.khanhtq.core.data.model.User

class UserSearchResponse {
    @SerializedName("total_count")
    val totalCount: Int = 0

    @SerializedName("items")
    val users: MutableList<User> = mutableListOf()
}