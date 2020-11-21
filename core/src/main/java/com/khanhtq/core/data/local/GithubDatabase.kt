package com.khanhtq.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.khanhtq.core.data.model.GithubTypeConverters
import com.khanhtq.core.data.model.Repo
import com.khanhtq.core.data.model.User
import com.khanhtq.core.data.model.UserSearchResult

@Database(entities = [User::class, Repo::class, UserSearchResult::class], version = 1)
@TypeConverters(GithubTypeConverters::class)
abstract class GithubDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}