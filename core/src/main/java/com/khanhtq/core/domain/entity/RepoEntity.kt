package com.khanhtq.core.domain.entity

data class RepoEntity(
        val id: Int,
        val name: String,
        val desc: String?,
        val star: Int
)