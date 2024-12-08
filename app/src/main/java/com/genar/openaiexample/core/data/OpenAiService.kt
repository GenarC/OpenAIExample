package com.genar.openaiexample.core.data

import com.genar.openaiexample.completion.data.CompletionRequestModel
import com.genar.openaiexample.completion.data.CompletionResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAiService {

    @POST(COMPLETION)
    suspend fun requestCompletion(@Body request: CompletionRequestModel): Response<CompletionResponseModel>

    companion object {
        const val COMPLETION = "chat/completions"
    }
}

