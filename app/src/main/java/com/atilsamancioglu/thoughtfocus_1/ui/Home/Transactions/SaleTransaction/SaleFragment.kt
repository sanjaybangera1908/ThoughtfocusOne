package com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.SaleTransaction

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.FragmentSaleBinding
import com.atilsamancioglu.thoughtfocus_1.db.TransactionAPP


class SaleFragment : Fragment(R.layout.fragment_sale) {
    private lateinit var viewModel: SaleViewModel
    lateinit var salebinding: FragmentSaleBinding
    lateinit var dialogView: View

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SaleViewModel::class.java)
        val transaction = childFragmentManager.beginTransaction()
        val binding = FragmentSaleBinding.bind(view)
        salebinding = binding

        val application = requireActivity().application
        val transactionDetailsDAO = (application as TransactionAPP).db.transactionDAO()
        salebinding.verifyButton.setOnClickListener {
            val dataToSend = salebinding.editTextNumber.text.toString()
            viewModel.amount.value = dataToSend
            viewModel.addRecord(dataToSend,transactionDetailsDAO)
            showAlertDialog()
            viewModel.transactionType.value = "Sale"
            viewModel.date.value = viewModel.getCurrentDate()
            viewModel.time.value = viewModel.getCurrentTime()
        }

    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select Transaction Type")

        val inflater = layoutInflater
        dialogView = inflater.inflate(R.layout.alert_dialog, null)
        builder.setView(dialogView)


        val radioButton1: RadioButton = dialogView.findViewById(R.id.radioButton1)
        val radioButton2: RadioButton = dialogView.findViewById(R.id.radioButton2)
        val radioButton3: RadioButton = dialogView.findViewById(R.id.radioButton3)

        builder.setPositiveButton("OK") { dialog, _ ->
            // Handle selection here
            when {
                radioButton1.isChecked -> navigateToFragment(ProgressFragment())
                radioButton2.isChecked -> navigateToFragment(ProgressFragment())
                radioButton3.isChecked -> navigateToFragment(ProgressFragment())
            }
            dialog.dismiss()
        }
        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = builder.create()

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        alertDialog.window?.setLayout(width, height)
        alertDialog.show()
    }

    private fun navigateToFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
