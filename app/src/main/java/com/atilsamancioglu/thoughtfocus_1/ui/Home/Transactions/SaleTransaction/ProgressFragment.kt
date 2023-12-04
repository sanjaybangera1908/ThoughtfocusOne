package com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.SaleTransaction

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.FragmentProgressBinding
import com.atilsamancioglu.thoughtfocus_1.ui.Home.HomeActivity
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.History.TransactionSuccesfulFragment


class ProgressFragment : Fragment() {

    private var progressStatus = 0
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var homeActivity: HomeActivity
    private lateinit var binding: FragmentProgressBinding // Replace with your actual binding class name

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgressBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar: ProgressBar = binding.progressBar

        Thread(Runnable {
            while (progressStatus < 100) {
                // Update the progress status on the main thread
                activity?.runOnUiThread {
                    progressBar.progress = progressStatus
                }

                try {

                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }


                progressStatus++
            }


            handler.postDelayed({
                navigateToFragment(TransactionSuccesfulFragment())
            }, 500) // Adjust the delay time as needed
        }).start()
        homeActivity = activity as HomeActivity
        homeActivity.hideOtherViews()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        // Show the other views when the fragment is destroyed or not visible
        homeActivity.showOtherViews()
    }

    private fun navigateToFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}
