package com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.History

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.FragmentTransactionHistoryBinding
import com.atilsamancioglu.thoughtfocus_1.db.Dao.TransactionDetailsDao
import com.atilsamancioglu.thoughtfocus_1.db.Entity.TransactionDetailsEntity
import com.atilsamancioglu.thoughtfocus_1.db.ItemAdapter
import com.atilsamancioglu.thoughtfocus_1.db.TransactionAPP
import kotlinx.coroutines.launch

class HistoryFragment : Fragment(R.layout.fragment_transaction_history) {


    lateinit var historybinding: FragmentTransactionHistoryBinding
    private lateinit var viewModel: TransactionHistoryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var binding = FragmentTransactionHistoryBinding.bind(view)
        historybinding = binding

        viewModel =
            ViewModelProvider(requireActivity()).get(TransactionHistoryViewModel::class.java)
        val application = requireActivity().application
        val transactionDetailsDAO = (application as TransactionAPP).db.transactionDAO()
        lifecycleScope.launch {
            transactionDetailsDAO.fetchAllTransactionDetails().collect{
                val list = ArrayList(it)
                setupListOfDataIntoRecyclerView(list,transactionDetailsDAO)
            }
        }
}
    private fun setupListOfDataIntoRecyclerView(transactionList:ArrayList<TransactionDetailsEntity>,
                                                transactionDetailsDAO: TransactionDetailsDao
    ) {
        if (transactionList.isNotEmpty()) {
            val itemAdapter = ItemAdapter(
                transactionList

            )
            historybinding.rvItemsList.layoutManager = LinearLayoutManager(context)
            historybinding.rvItemsList.adapter = itemAdapter
            historybinding.rvItemsList.visibility = View.VISIBLE
            historybinding.textView3.visibility = View.GONE
        }
        else{
            historybinding.rvItemsList.visibility = View.GONE
            historybinding.textView3.visibility = View.VISIBLE
        }
    }
}

