package com.genar.openaiexample.completion.data

import com.google.gson.annotations.SerializedName

data class CompletionResponseModel(
    val id: String,
    @SerializedName("object")
    val obj: String,
    val created: Long,
    val model: String,
    @SerializedName("system_fingerprint")
    val systemFingerprint: String,
    val choices: List<Choice>,
    val usage: Usage
) {
    //Create empty object
    companion object {
        var EMPTY = CompletionResponseModel(
            id = "",
            obj = "",
            created = 0,
            model = "",
            systemFingerprint = "",
            choices = emptyList(),
            usage = Usage(
                promptTokens = 0,
                completionTokens = 0,
                totalTokens = 0,
                completionTokensDetails = CompletionTokensDetails(
                    reasoningTokens = 0,
                    acceptedPredictionTokens = 0,
                    rejectedPredictionTokens = 0
                )
            )
        )
    }
}

data class Choice(
    val index: Int,
    val message: Message,
    val logprobs: Any?,
    @SerializedName("finish_reason")
    val finishReason: String
)

data class Message(
    val role: String,
    val content: String
)

data class Usage(
    @SerializedName("prompt_tokens")
    val promptTokens: Int,
    @SerializedName("completion_tokens")
    val completionTokens: Int,
    @SerializedName("total_tokens")
    val totalTokens: Int,
    @SerializedName("completion_tokens_details")
    val completionTokensDetails: CompletionTokensDetails
)

data class CompletionTokensDetails(
    @SerializedName("reasoning_tokens")
    val reasoningTokens: Int,
    @SerializedName("accepted_prediction_tokens")
    val acceptedPredictionTokens: Int,
    @SerializedName("rejected_prediction_tokens")
    val rejectedPredictionTokens: Int
)
