package com.example.room_db_ss.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room_db_ss.model.User

@Dao

interface UserDao {
    // Dao must be an interface
    //Dao contains the methods for accessing a DB

    @Insert(onConflict = OnConflictStrategy.IGNORE)
   suspend fun addUser(user: User)

   @Update
   suspend fun updateUser(user: User)

   @Delete
   suspend fun deleteUser(user: User)

   @Query("DELETE FROM user_table")
   suspend fun deleteAllUsers()

   @Query("SELECT * FROM user_table ORDER BY id ASC")
   fun readAllData(): LiveData<List<User>>
}