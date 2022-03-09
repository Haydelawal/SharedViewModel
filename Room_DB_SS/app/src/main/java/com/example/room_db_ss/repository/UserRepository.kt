package com.example.room_db_ss.repository

import androidx.lifecycle.LiveData
import com.example.room_db_ss.data.UserDao
import com.example.room_db_ss.model.User

class UserRepository(private val userDao: UserDao) {
    // UserRepository
    // A Repository class abstracts access to multiple data sources

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}