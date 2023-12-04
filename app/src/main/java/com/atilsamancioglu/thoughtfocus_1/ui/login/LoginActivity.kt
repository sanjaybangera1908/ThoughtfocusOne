package com.atilsamancioglu.thoughtfocus_1.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.atilsamancioglu.thoughtfocus_1.R
import com.atilsamancioglu.thoughtfocus_1.databinding.ActivityLoginBinding
import com.atilsamancioglu.thoughtfocus_1.db.ApplicationDatabase
import com.atilsamancioglu.thoughtfocus_1.ui.Home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var appdb: ApplicationDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        appdb = ApplicationDatabase.getInstance(applicationContext)
        Log.d("Database", "Database created successfully")

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.isLoginButtonEnabled.observe(this) { isEnabled ->
            binding.loginButton.isEnabled = isEnabled
        }

        viewModel.loginResult.observe(this) { loginSuccessful ->
            if (loginSuccessful) {
                viewModel.writeDataToDatabase()
                startActivity(Intent(this, HomeActivity::class.java))
            }
        }

        binding.loginButton.setOnClickListener {
            viewModel.onLoginClick()
        }

        binding.usernameEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onUserIdChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onPasswordChanged(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
