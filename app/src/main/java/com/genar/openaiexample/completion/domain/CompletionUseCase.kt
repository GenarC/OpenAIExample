package com.genar.openaiexample.completion.domain

import com.genar.openaiexample.completion.data.OpenAiRepository
import com.genar.openaiexample.completion.mapper.MessageUIMapper
import com.genar.openaiexample.completion.model.MessageUIModel
import javax.inject.Inject

class CompletionUseCase @Inject constructor(
    private val openAiRepository: OpenAiRepository,
    private val messageUIMapper: MessageUIMapper
) {
    suspend operator fun invoke(prompt: String): MessageUIModel {
        return messageUIMapper.mapper(
            openAiRepository.completion(prompt)
        )
    }
}