package com.genar.openaiexample.di

import com.genar.openaiexample.BuildConfig
import com.genar.openaiexample.core.data.OpenAiService
import com.genar.openaiexample.utils.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideBaseUrl(): String = "https://api.openai.com/v1/"

    @Provides
    @Singleton
    fun provideOkHttpClient(
        authorizationInterceptor: AuthorizationInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            addInterceptor(authorizationInterceptor)
            addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
        }.build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.OPENAI_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesProductService(retrofit: Retrofit): OpenAiService =
        retrofit.create(OpenAiService::class.java)
}