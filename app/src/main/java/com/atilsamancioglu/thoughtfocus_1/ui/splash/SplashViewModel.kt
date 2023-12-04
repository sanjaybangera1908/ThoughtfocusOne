package com.atilsamancioglu.thoughtfocus_1.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : ViewModel() {
    private val _navigateToLogin = MutableLiveData<Boolean>()
    val navigateToLogin: LiveData<Boolean>
        get() = _navigateToLogin

    init {
        viewModelScope.launch {

            delay(5000)
            _navigateToLogin.postValue(true)
        }
    }

    fun onNavigationHandled() {
        _navigateToLogin.value = false

    }
}

