package com.atilsamancioglu.thoughtfocus_1.ui.splash
// SplashActivity.kt
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.ActivitySplashBinding
import com.atilsamancioglu.thoughtfocus_1.ui.login.LoginActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivitySplashBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_splash
        )

        val viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        observeNavigation()
    }

    private fun observeNavigation() {
        val viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        viewModel.navigateToLogin.observe(this) { navigate ->
            if (navigate) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                viewModel.onNavigationHandled()
            }
        }
    }
}