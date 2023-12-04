package com.atilsamancioglu.thoughtfocus_1.db.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atilsamancioglu.thoughtfocus_1.db.Entity.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll():List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE  FROM user_table")
    suspend fun deleteAll()

}