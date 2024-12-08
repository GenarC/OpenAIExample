package com.genar.openaiexample.completion.mapper

import com.genar.openaiexample.completion.data.CompletionResponseModel
import com.genar.openaiexample.completion.model.MessageType
import com.genar.openaiexample.completion.model.MessageUIModel
import javax.inject.Inject

class MessageUIMapper @Inject constructor() {
    fun mapper(response: CompletionResponseModel): MessageUIModel {
        return MessageUIModel(
            message = response.choices.first().message.content,
            type = MessageType.RECEIVED
        )
    }
}