package com.khanhtq.core.domain.entity

data class UserEntity(
    val userName: String,
    val realName: String?,
    val avatar: String?,
    val company: String?,
    val blog: String?
)