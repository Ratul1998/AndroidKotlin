package com.example.myapp.repositories

import androidx.lifecycle.LiveData
import com.example.myapp.data.user.User
import com.example.myapp.data.user.UserDao
import javax.inject.Inject

class AuthRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun addUser(user:User){
        userDao.addUser(user)
    }

    suspend fun getUserByEmail(email:String): List<User> {
       return userDao.getUserByEmail(email)
    }

    suspend fun getUserById(id:Int) : List<User> {
        return userDao.getUserById(id)
    }

}