package com.atilsamancioglu.thoughtfocus_1.db.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "transactionDetails")
    data class TransactionDetailsEntity(
        @PrimaryKey(autoGenerate = true)
        val id:Int = 0,
        val type:String,
        val date:String,
        val amount : String = ""
    )

