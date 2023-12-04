package com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.SaleTransaction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atilsamancioglu.thoughtfocus_1.db.Dao.TransactionDetailsDao
import com.atilsamancioglu.thoughtfocus_1.db.Entity.TransactionDetailsEntity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class SaleViewModel :ViewModel() {
    val amount = MutableLiveData<String>()
    val transactionType = MutableLiveData<String>()
    val  date= MutableLiveData<String>()
    val  time= MutableLiveData<String>()
    fun addRecord(amount: String, transactionDetailsDAO: TransactionDetailsDao) {
        if (amount.isNotEmpty()) {
            val currentDate = getCurrentDate()
            viewModelScope.launch {
                transactionDetailsDAO.insert(TransactionDetailsEntity(type = "Sale", amount=amount, date = currentDate))
            }
        }
    }

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.format(Date())
    }
    fun getCurrentTime(): String {
        val timeFormat = SimpleDateFormat("HH:mm:ss")
        return timeFormat.format(Date())
    }

}