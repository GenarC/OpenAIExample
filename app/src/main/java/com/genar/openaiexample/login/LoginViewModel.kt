package com.genar.openaiexample.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genar.openaiexample.di.DispatcherModule
import com.genar.openaiexample.login.data.LoginResponseModel
import com.genar.openaiexample.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @DispatcherModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    fun loginClicked(email: String, password: String) {
        viewModelScope.launch {
            val a = loginUseCase.invoke(email, password)
            val b = "a"
        }
    }
}