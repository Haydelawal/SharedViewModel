package com.example.embeddedroomlibrarytest_ss.data

import androidx.lifecycle.LiveData
import com.example.embeddedroomlibrarytest_ss.data.MyDao

class MyRepository(private val myDao: MyDao) {

    val readPerson: LiveData<List<Person>> = myDao.readPerson()

    suspend fun insertPerson(person: Person){
        myDao.insertPerson(person)
    }


}