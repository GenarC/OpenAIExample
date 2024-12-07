package com.genar.openaiexample.core.data

import com.genar.openaiexample.login.data.LoginRequestModel
import com.genar.openaiexample.login.data.LoginResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAiService {
    @POST("login")
    fun login(@Body request: LoginRequestModel): Response<LoginResponseModel>

    companion object {
        const val BASE_URL = "https://api.openai.com/v1/"
    }
}