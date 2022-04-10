package com.naky.onetoonerelationshiprd.repository

import androidx.lifecycle.LiveData
import com.naky.onetoonerelationshiprd.dao.UserDao
import com.naky.onetoonerelationshiprd.entity.Library
import com.naky.onetoonerelationshiprd.entity.User
import com.naky.onetoonerelationshiprd.entity.UserAndLibrarry

class UserRepository(private val userDao: UserDao) {

    var readAllData : LiveData<List<UserAndLibrarry>>? = null

    suspend fun addUser(item : List<User>){
        userDao.insertuser(item)
    }

    suspend fun  addLibrary(item: List<Library>){
        userDao.insertLibrary(item)
    }

    fun getUserData(userId : Int) : List<UserAndLibrarry>{
        return userDao.getUserAndLibraries(userId = userId)
    }

}