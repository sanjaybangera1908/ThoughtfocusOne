package com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.RefundTransaction

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.FragmentRefundBinding
import com.atilsamancioglu.thoughtfocus_1.db.TransactionAPP
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.SaleTransaction.ProgressFragment

class RefundFragment : Fragment(R.layout.fragment_refund) {
    private lateinit var refundFragment: RefundFragment
    lateinit var refundBinding: FragmentRefundBinding
    lateinit var viewModel:RefundViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(RefundViewModel::class.java)
        val transaction = childFragmentManager.beginTransaction()
        val binding = FragmentRefundBinding.bind(view)
        refundBinding = binding

        val application = requireActivity().application
        val transactionDetailsDAO = (application as TransactionAPP).db.transactionDAO()
        refundBinding.veriyButton1.setOnClickListener {
            val dataToSend = refundBinding.editTextNumber.text.toString()
            viewModel.sharedData.value = dataToSend
            viewModel.addRecord(dataToSend, transactionDetailsDAO)

            // Navigate to your desired fragment here (replace with the actual fragment you want to open)
            val newFragment = ProgressFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .addToBackStack(null) // Optional: Add the transaction to the back stack
                .commit()
        }
    }
}