package com.khanhtq.core.mapper

import com.khanhtq.core.data.model.Repo
import com.khanhtq.core.data.model.User
import com.khanhtq.core.domain.entity.RepoEntity
import com.khanhtq.core.domain.entity.UserEntity

fun User.toEntity(): UserEntity = UserEntity(
        userName = this.login,
        realName = this.name,
        avatar = this.avatarUrl,
        company = this.company,
        blog = this.blog
)

fun Repo.toEntity(): RepoEntity = RepoEntity(
        id = id,
        name = fullName,
        desc = description,
        star = stars
)