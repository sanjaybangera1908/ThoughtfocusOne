package com.atilsamancioglu.thoughtfocus_1.db

import android.app.Application

class TransactionAPP: Application() {
    val db by lazy{
        ApplicationDatabase.getInstance(this)
    }
}