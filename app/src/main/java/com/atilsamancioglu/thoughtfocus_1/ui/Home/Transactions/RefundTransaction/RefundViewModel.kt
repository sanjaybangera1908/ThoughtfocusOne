package com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.RefundTransaction

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atilsamancioglu.thoughtfocus_1.db.Dao.TransactionDetailsDao
import com.atilsamancioglu.thoughtfocus_1.db.Entity.TransactionDetailsEntity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class RefundViewModel:ViewModel() {
    val sharedData = MutableLiveData<String>()
    fun addRecord(amount: String, transactionDetailsDAO: TransactionDetailsDao) {
        if (amount.isNotEmpty()) {
            val currentDate = getCurrentDate()
            viewModelScope.launch {
                transactionDetailsDAO.insert(TransactionDetailsEntity(type = "Refund", amount=amount, date = currentDate))
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.format(Date())
    }

}