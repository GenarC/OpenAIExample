package com.genar.openaiexample.completion.data

import com.genar.openaiexample.core.data.OpenAiService
import javax.inject.Inject

class OpenAiRepository @Inject constructor(
    private val openAiService: OpenAiService
) {
    suspend fun completion(prompt: String): CompletionResponseModel {

        val message = MessageEntity(
            role = ROLE,
            content = prompt
        )
        return openAiService.requestCompletion(
            CompletionRequestModel(
                model = MODEL,
                messages = listOf(message)
            )
        ).body() ?: return CompletionResponseModel.EMPTY
    }

    companion object{
        var MODEL = "chatgpt-4o-latest"
        var ROLE = "user"
    }
}