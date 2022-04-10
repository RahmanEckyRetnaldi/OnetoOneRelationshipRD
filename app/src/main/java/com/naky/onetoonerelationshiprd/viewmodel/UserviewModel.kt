package com.naky.onetoonerelationshiprd.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.naky.onetoonerelationshiprd.database.UserDatabase
import com.naky.onetoonerelationshiprd.entity.Library
import com.naky.onetoonerelationshiprd.entity.User
import com.naky.onetoonerelationshiprd.entity.UserAndLibrarry
import com.naky.onetoonerelationshiprd.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _readAllData = MutableLiveData<List<UserAndLibrarry>>()
    var readAllData: LiveData<List<UserAndLibrarry>> = _readAllData
    val repository: UserRepository

    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
    }

    fun getUser(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _readAllData.postValue(repository.getUserData(userId))
        }
    }

    fun addUser(item: List<User>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(item)
        }
    }

    fun addLibrary(item: List<Library>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLibrary(item)
        }
    }

}

