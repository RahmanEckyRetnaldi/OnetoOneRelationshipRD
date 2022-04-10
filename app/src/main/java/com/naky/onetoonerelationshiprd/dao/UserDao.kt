package com.naky.onetoonerelationshiprd.dao

import androidx.room.*
import com.naky.onetoonerelationshiprd.entity.Library
import com.naky.onetoonerelationshiprd.entity.User
import com.naky.onetoonerelationshiprd.entity.UserAndLibrarry


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertuser(item : List<User>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLibrary(item: List<Library>)

    @Transaction
    @Query("SELECT * FROM User WHERE userId = :userId")
    fun getUserAndLibraries(userId : Int) : List<UserAndLibrarry>
}