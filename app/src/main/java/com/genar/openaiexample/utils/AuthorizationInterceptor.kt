package com.genar.openaiexample.utils

import com.genar.openaiexample.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder().apply {
            addHeader("Authorization","Bearer ${ BuildConfig.OPENAI_SECRET_KEY }")
            addHeader("Content-Type","application/json")
        }.build()
        return chain.proceed(builder)
    }
}