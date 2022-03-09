package com.example.room_db_ss.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName =  "user_table")
data class User (

    // THIS IS THE ENTITY CLASS.... IT REPRESENTS A TABLE WITHIN THE DB
@PrimaryKey(autoGenerate = true)
val id: Int,
val firstName: String,
val lastName: String,
val age: Int,

): Parcelable