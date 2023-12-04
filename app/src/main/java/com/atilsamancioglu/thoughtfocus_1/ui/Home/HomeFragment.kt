package com.atilsamancioglu.thoughtfocus_1.ui.Home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.FragmentHomeBinding


class HomeFragment : Fragment(R.layout.fragment_home) {
    lateinit var viewModel: HomeViewModel
    var homeBinding : FragmentHomeBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        val binding = FragmentHomeBinding.bind(view)
        homeBinding = binding
        binding.cardView1.setOnClickListener {
            toggleVisibility(homeBinding!!.hiddenLayout1)
        }

    }
    private fun toggleVisibility(view: View) {
        if (view.visibility == View.VISIBLE) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

}