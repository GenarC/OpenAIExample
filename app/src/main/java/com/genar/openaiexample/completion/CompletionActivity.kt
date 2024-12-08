package com.genar.openaiexample.completion

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.genar.openaiexample.completion.model.CompletionViewModel
import com.genar.openaiexample.completion.model.MessageType
import com.genar.openaiexample.completion.model.MessageUIModel
import com.genar.openaiexample.databinding.ActivityCompletionBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CompletionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompletionBinding
    private val viewModel: CompletionViewModel by viewModels()

    private val messageAdapter = MessageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompletionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initObservers()
        initOnCreate()
    }

    private fun initViews() = with(binding) {
        val background = binding.root.background
        if (background is TransitionDrawable) {
            background.startTransition(1000) // 1-second transition
        }

        recyclerViewMessages.apply {
            layoutManager =
                LinearLayoutManager(this@CompletionActivity, LinearLayoutManager.VERTICAL, false)
            adapter = messageAdapter
        }

        recyclerViewMessages.itemAnimator = object : DefaultItemAnimator() {
            override fun animateAdd(holder: RecyclerView.ViewHolder?): Boolean {
                holder?.itemView?.apply {
                    translationY = 100f
                    alpha = 0f
                    animate()
                        .translationY(0f)
                        .alpha(1f)
                        .setDuration(500)
                        .start()
                }
                return super.animateAdd(holder)
            }
        }

        etPrompt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                btnSend.performClick() // Trigger the send button click
                true // Consume the action
            } else {
                false // Let other actions continue
            }
        }


        btnSend.setOnClickListener {
            if (etPrompt.text.isEmpty()) {
                return@setOnClickListener
            }

            it.animate()
                .scaleX(0.9f)
                .scaleY(0.9f)
                .setDuration(100)
                .withEndAction {
                    it.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100)
                        .start()
                }.start()

            viewModel.sendButtonClicked(
                prompt = etPrompt.text.toString()
            )

            addMessageToList(
                MessageUIModel(
                    message = etPrompt.text.toString(),
                    type = MessageType.SENT
                )
            )
            etPrompt.text.clear()
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.responseFlow.collect { response ->
                addMessageToList(response)
            }
        }
    }

    private fun initOnCreate() {
        viewModel.init()
    }

    private fun addMessageToList(msg: MessageUIModel){
        messageAdapter.addMessage(msg)
        binding.recyclerViewMessages.post {
            binding.recyclerViewMessages.smoothScrollToPosition(messageAdapter.getSize() - 1)
        }
    }


}