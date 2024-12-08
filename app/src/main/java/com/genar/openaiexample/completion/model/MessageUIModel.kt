package com.genar.openaiexample.completion.model

data class MessageUIModel (
    val message: String,
    val type: MessageType
)

data class ChoiceUIModel (
    val text: String,
    val index: Int,
)

enum class MessageType(val value: Int) {
    SENT(1),
    RECEIVED(2);

    companion object {
        fun fromInt(value: Int) = entries.first { it.value == value }
    }
}