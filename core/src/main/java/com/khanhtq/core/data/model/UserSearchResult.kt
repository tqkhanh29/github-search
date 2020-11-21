package com.khanhtq.core.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Entity
data class UserSearchResult(
    @PrimaryKey
    val query: String,
    val users: List<String>,
    val total: Int
)