package com.atilsamancioglu.thoughtfocus_1.db.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "User_name") val userName:String?,
    @ColumnInfo(name = "User_Password") val userPassword:String?,)
