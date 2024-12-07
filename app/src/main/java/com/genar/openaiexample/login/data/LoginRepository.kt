package com.genar.openaiexample.login.data

import com.genar.openaiexample.core.data.OpenAiService
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val openAiService: OpenAiService
) {
    suspend fun login(email: String, password: String): LoginResponseModel {
        return openAiService.login(LoginRequestModel(email, password)).body() ?: return LoginResponseModel.EMPTY
    }
}