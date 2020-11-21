package com.khanhtq.core.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.khanhtq.core.data.model.User
import com.khanhtq.core.data.model.UserSearchResult
import com.khanhtq.core.data.model.UserWithRepos
import kotlinx.coroutines.flow.Flow

@Dao
abstract class UserDao {

    @Transaction
    @Query("SELECT * FROM User WHERE login IS :userName ")
    abstract fun getUserWithRepos(userName: String): Flow<UserWithRepos?>

    @Query("SELECT * FROM User WHERE login IS :userName")
    abstract fun getUser(userName: String): Flow<User?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUsers(users: List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertUserSearchResult(userSearchResult: UserSearchResult)

    @Query("SELECT * FROM UserSearchResult WHERE `query` = :query")
    abstract fun search(query: String): Flow<UserSearchResult?>

    @Query("SELECT * FROM User WHERE login in (:userNames)")
    abstract fun loadUsersByUsernames(userNames: List<String>): Flow<List<User>>
}