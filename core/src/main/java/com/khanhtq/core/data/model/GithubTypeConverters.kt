package com.khanhtq.core.data.model

import androidx.room.TypeConverter

object GithubTypeConverters {
    @TypeConverter
    @JvmStatic
    fun stringToIntList(data: String?): List<String>? {
        return data?.split(",")
    }

    @TypeConverter
    @JvmStatic
    fun intListToString(ints: List<String>?): String? {
        return ints?.joinToString(",")
    }
}