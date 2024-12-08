package com.genar.openaiexample.completion

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.genar.openaiexample.completion.model.MessageType
import com.genar.openaiexample.completion.model.MessageUIModel
import com.genar.openaiexample.databinding.ItemMessageReceivedBinding
import com.genar.openaiexample.databinding.ItemMessageSentBinding

class MessageAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val messageList: ArrayList<MessageUIModel> = arrayListOf()

    override fun getItemViewType(position: Int): Int {
        return messageList[position].type.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == MessageType.SENT.value) {
            SentMessageViewHolder(
                ItemMessageSentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            ReceivedMessageViewHolder(
                ItemMessageReceivedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messageList[position]
        when (holder) {
            is SentMessageViewHolder -> holder.bind(message)
            is ReceivedMessageViewHolder -> holder.bind(message)
        }
    }

    fun addMessage(message: MessageUIModel) {
        messageList.add(message)
        notifyItemInserted(messageList.size - 1)
    }

    fun getSize () : Int {
        return messageList.size
    }

    override fun getItemCount(): Int = messageList.size

    class SentMessageViewHolder(private var binding: ItemMessageSentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageUIModel) {
            binding.textViewMessage.text = message.message
        }
    }

    class ReceivedMessageViewHolder(private var binding: ItemMessageReceivedBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(message: MessageUIModel) {
            binding.textViewMessage.text = message.message
        }
    }
}