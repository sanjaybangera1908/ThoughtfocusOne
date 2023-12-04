package com.atilsamancioglu.thoughtfocus_1.ui.Home

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.ActivityHomeBinding
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.History.HistoryFragment
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.RefundTransaction.RefundFragment
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.SaleTransaction.SaleFragment
import com.atilsamancioglu.thoughtfocus_1.ui.Home.Transactions.Void.VoidFragment
import com.atilsamancioglu.thoughtfocus_1.ui.login.LoginActivity
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var fragmentManager: FragmentManager
    private var isFragmentAttached: Boolean = false
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager = supportFragmentManager

        val toggle = ActionBarDrawerToggle(
            this, binding.drawerLayout,
            R.string.open,
            R.string.close
        )

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        binding.navigationDrawer.setNavigationItemSelectedListener(this)
        binding.bottomNavigation.background = null

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            handleBottomNavigationItemSelected(item)
        }
        fragmentManager = supportFragmentManager
        binding.bottomNavigation.selectedItemId = R.id.home
    }

    private fun handleBottomNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sale -> openFragment(SaleFragment())
            R.id.history -> openFragment(HistoryFragment())
            R.id.home -> openFragment(HomeFragment())
            R.id.refund_transaction -> openFragment(RefundFragment())
            R.id.void_transaction -> openFragment(VoidFragment())
            else -> return false
        }
        return true
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sale_refund -> openFragment(RefundFragment())
            R.id.sale_void -> openFragment(VoidFragment())
            R.id.home -> openFragment(HomeFragment())
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun openFragment(fragment: Fragment) {
        try {
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()

        showLogoutConfirmationDialog()
    }

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Logout")
        builder.setMessage("Are you sure you want to logout?")

        builder.setPositiveButton("Yes") { dialog, which ->

            navigateToLoginPage()
        }

        builder.setNegativeButton("No") { dialog, which ->

            Toast.makeText(this, "Logout canceled", Toast.LENGTH_SHORT).show()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun navigateToLoginPage() {

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

    }
    fun hideOtherViews() {
        binding.cordi.visibility = View.GONE
        binding.TopView.visibility=View.GONE
        binding.imageView3.visibility=View.GONE
        binding.navigationDrawer.visibility = View.GONE
    }

    fun showOtherViews() {
        binding.cordi.visibility = View.VISIBLE
        binding.navigationDrawer.visibility = View.VISIBLE
        binding.TopView.visibility=View.VISIBLE
        binding.imageView3.visibility=View.VISIBLE
    }
}