package com.genar.openaiexample.login.domain

import com.genar.openaiexample.login.data.LoginRepository
import com.genar.openaiexample.login.data.LoginResponseModel
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(email: String, password: String): LoginResponseModel {
        return loginRepository.login(email, password)
    }
}