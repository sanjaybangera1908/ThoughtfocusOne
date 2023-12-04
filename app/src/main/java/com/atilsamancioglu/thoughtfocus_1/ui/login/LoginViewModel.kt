package com.atilsamancioglu.thoughtfocus_1.ui.login
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atilsamancioglu.thoughtfocus_1.db.ApplicationDatabase
import com.atilsamancioglu.thoughtfocus_1.db.Entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(application: Application):AndroidViewModel(application) {

    private val _userId = MutableLiveData<String>()
    val userId: LiveData<String>
        get() = _userId

    private val _password = MutableLiveData<String>()
    val password: LiveData<String>
        get() = _password

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    private val _isLoginButtonEnabled = MutableLiveData<Boolean>()
    val isLoginButtonEnabled: LiveData<Boolean>
        get() = _isLoginButtonEnabled

    init {
        _userId.value = ""
        _password.value = ""
        _isLoginButtonEnabled.value = false
    }

    fun onUserIdChanged(newUserId: String) {
        _userId.value = newUserId
        updateLoginButtonState()
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
        updateLoginButtonState()
    }

    private fun updateLoginButtonState() {
        val userIdNotEmpty = _userId.value.orEmpty().isNotEmpty()
        val passwordNotEmpty = _password.value.orEmpty().isNotEmpty()
        _isLoginButtonEnabled.value = userIdNotEmpty && passwordNotEmpty
    }

    fun onLoginClick() {
        val currentUserId = _userId.value.orEmpty()
        val currentPassword = _password.value.orEmpty()

        Log.d("LoginViewModel", "User ID: $currentUserId, Password: $currentPassword")

        viewModelScope.launch {
            delay(2000)

            val loginSuccessful = checkLogin(currentUserId, currentPassword)
            _loginResult.value = loginSuccessful

            Log.d("LoginViewModel", "Login Result: $loginSuccessful")
        }
    }

    fun writeDataToDatabase() {
        val username = _userId.value.orEmpty()
        val password = _password.value.orEmpty()

        val user = User(null,username, password)

        viewModelScope.launch(Dispatchers.IO) {
            ApplicationDatabase.getInstance(getApplication()).userDao().insert(user)
        }
    }

    private fun checkLogin(username: String, password: String): Boolean {
        val isValid = username.isNotEmpty() && password.isNotEmpty()
        _isLoginButtonEnabled.value = isValid
        return isValid
    }

}