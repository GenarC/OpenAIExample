package com.genar.openaiexample.completion.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genar.openaiexample.di.DispatcherModule
import com.genar.openaiexample.completion.domain.CompletionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompletionViewModel @Inject constructor(
    @DispatcherModule.IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val completionUseCase: CompletionUseCase
) : ViewModel() {

    private val response = MutableSharedFlow<MessageUIModel>()
    val responseFlow: SharedFlow<MessageUIModel>
        get() = response

    fun init() {
        viewModelScope.launch(ioDispatcher) {
            response.emit(completionUseCase.invoke(prompt = INIT_PROMPT))
        }
    }

    fun sendButtonClicked(prompt: String) {
        viewModelScope.launch(ioDispatcher) {
            response.emit(completionUseCase.invoke(prompt = prompt))
        }
    }

    companion object {
        const val INIT_PROMPT = "Give me a chat starter sentence for an AI chatbot"
    }
}