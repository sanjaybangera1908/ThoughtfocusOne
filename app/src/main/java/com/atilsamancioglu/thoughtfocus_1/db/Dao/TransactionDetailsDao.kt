package com.atilsamancioglu.thoughtfocus_1.db.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.atilsamancioglu.thoughtfocus_1.db.Entity.TransactionDetailsEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface TransactionDetailsDao {

    @Insert
    suspend fun insert(transactionDetails: TransactionDetailsEntity)

    @Update
    suspend fun update(transactionDetails: TransactionDetailsEntity)

    @Delete
    suspend fun delete(transactionDetails: TransactionDetailsEntity)

    @Query("SELECT * FROM `transactionDetails`")
    fun fetchAllTransactionDetails(): Flow<List<TransactionDetailsEntity>>

    @Query("SELECT * FROM `transactionDetails` where id=:id")
    fun fetchTransactionDetails(id:Int): Flow<TransactionDetailsEntity>
}
