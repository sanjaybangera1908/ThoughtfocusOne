package com.atilsamancioglu.thoughtfocus_1.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atilsamancioglu.thoughtfocus_1.db.Dao.TransactionDetailsDao
import com.atilsamancioglu.thoughtfocus_1.db.Dao.UserDao
import com.atilsamancioglu.thoughtfocus_1.db.Entity.TransactionDetailsEntity
import com.atilsamancioglu.thoughtfocus_1.db.Entity.User

@Database(entities = [User::class, TransactionDetailsEntity::class], version = 2)
abstract class ApplicationDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
    abstract fun transactionDAO():TransactionDetailsDao
    companion object{
        @Volatile
        private var INSTANCE: ApplicationDatabase? = null
        fun getInstance(context: Context):ApplicationDatabase{
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ApplicationDatabase::class.java,
                        name = "thoughtfocus_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}