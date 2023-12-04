package com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.Void

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.FragmentVoidBinding
import com.atilsamancioglu.thoughtfocus_1.db.TransactionAPP
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.SaleTransaction.ProgressFragment


class VoidFragment : Fragment(R.layout.fragment_void) {
    private lateinit var voidFragment: VoidFragment
    lateinit var voidBinding: FragmentVoidBinding
    lateinit var voidViewModel: VoidViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        voidViewModel = ViewModelProvider(requireActivity()).get(VoidViewModel::class.java)
        val transaction = childFragmentManager.beginTransaction()
        val binding = FragmentVoidBinding.bind(view)
        voidBinding= binding

        val application = requireActivity().application
        val transactionDetailsDAO = (application as TransactionAPP).db.transactionDAO()
        voidBinding.voidveriyButton.setOnClickListener {
            val dataToSend = voidBinding.editTextNumber.text.toString()
            voidViewModel.sharedData.value = dataToSend
            voidViewModel.addRecord(dataToSend, transactionDetailsDAO)

            // Navigate to your desired fragment here (replace with the actual fragment you want to open)
            val newFragment = ProgressFragment()
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, newFragment)
                .addToBackStack(null) // Optional: Add the transaction to the back stack
                .commit()
        }
    }
}




