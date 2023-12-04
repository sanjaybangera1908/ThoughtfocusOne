package com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.History

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.FragmentTransactionSuccesfulBinding
import com.atilsamancioglu.thoughtfocus_1.ui.Home.HomeActivity
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.RefundTransaction.RefundViewModel
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.SaleTransaction.SaleViewModel
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.Void.VoidViewModel

class TransactionSuccesfulFragment : Fragment(R.layout.fragment_transaction_succesful) {
    lateinit var binding: FragmentTransactionSuccesfulBinding


    private lateinit var sharedViewModel: SaleViewModel
    private lateinit var sharedViewModelVoidViewModel: VoidViewModel
    private lateinit var sharedRefundViewModel: RefundViewModel
    private lateinit var homeActivity: HomeActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTransactionSuccesfulBinding.bind(view)

        sharedViewModel = ViewModelProvider(requireActivity()).get(SaleViewModel::class.java)
        binding.tvTypes.text = "Type"
        binding.tvAmt.text = "Amount"
        binding.tvDate.text = "Date"
        binding.tvTimes .text= "Time"
        sharedViewModel.transactionType.observe(viewLifecycleOwner, Observer {
            val type = it
            binding.tvType.text = type
        })
        sharedViewModel.amount.observe(viewLifecycleOwner, Observer {
            val amount = it
            binding.tvAmount.text = amount
        })
        sharedViewModel.date.observe(viewLifecycleOwner, Observer {
            val date = it
            binding.tvCurrentDate.text = date
        })
        sharedViewModel.time.observe(viewLifecycleOwner, Observer {
            val time = it
            binding.tvTime.text = time
        })

        homeActivity = activity as HomeActivity
        homeActivity.hideOtherViews()
    }


}