package com.genar.openaiexample.completion.data

data class CompletionRequestModel (
    val messages: List<MessageEntity>,
    val model: String,
)

data class MessageEntity (
    val role: String,
    val content: String,
)