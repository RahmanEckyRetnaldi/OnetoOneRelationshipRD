package com.naky.onetoonerelationshiprd.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.naky.onetoonerelationshiprd.dao.UserDao
import com.naky.onetoonerelationshiprd.entity.Library
import com.naky.onetoonerelationshiprd.entity.User

@Database(entities = [User::class, Library::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE : UserDatabase? = null

        fun getInstance(context: Context) : UserDatabase{
            val userInstance = INSTANCE
            if(userInstance != null){
                return userInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_and_library"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}