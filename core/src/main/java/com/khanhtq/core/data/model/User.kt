package com.khanhtq.core.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName


@Entity(primaryKeys = ["login"])
data class User(
    @SerializedName("login")
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("blog")
    val blog: String?
)