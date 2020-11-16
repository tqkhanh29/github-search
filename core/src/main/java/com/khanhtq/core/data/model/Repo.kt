package com.khanhtq.core.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity(
    indices = [
        Index("id"),
        Index("owner_login")],
    primaryKeys = ["name", "owner_login"]
)
data class Repo(
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("full_name")
    val fullName: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("owner")
    @Embedded(prefix = "owner_")
    val owner: Owner,
    @SerializedName("stargazers_count")
    val stars: Int
) {

    data class Owner(
        @SerializedName("login")
        val login: String
    )
}

data class UserWithRepos(
    @Embedded val user: User,
    @Relation(
        parentColumn = "login",
        entityColumn = "owner_login"
    )
    val repos: List<Repo>
)