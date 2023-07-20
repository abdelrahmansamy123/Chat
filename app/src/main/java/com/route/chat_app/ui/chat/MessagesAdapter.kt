package com.route.chat_app.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.route.chat_app.R
import com.route.chat_app.UserProvider
import com.route.chat_app.database.models.Message
import com.route.chat_app.databinding.ItemRecievedMessageBinding
import com.route.chat_app.databinding.ItemSendMessageBinding

enum class MessageType(val value:Int){
    Received(1),
    Send(2)
}

class MessagesAdapter(var messages: MutableList<Message>)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        if (message.senderId == UserProvider.user?.id){
            return MessageType.Send.value
        }
        return MessageType.Received.value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
       if (viewType == MessageType.Send.value){
        val viewBinding: ItemSendMessageBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context),
            R.layout.item_send_message,parent,false)
           return SendMessageViewHolder(viewBinding)
       }
        val viewBinding: ItemRecievedMessageBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context),
                R.layout.item_recieved_message,parent,false)
        return ReceivedMessageViewHolder(viewBinding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       if (holder is SendMessageViewHolder){
            holder.bind(messages[position])
       }
        if (holder is ReceivedMessageViewHolder){
            holder.bind(messages[position])
        }
    }

    override fun getItemCount(): Int = messages.size

    fun addMessage(message : Message){
        messages.add(message)
        notifyItemInserted(messages.size)
    }

    class SendMessageViewHolder(val viewBinding: ItemSendMessageBinding)
        :RecyclerView.ViewHolder(viewBinding.root){
            fun bind(message: Message){
                viewBinding.message = message
                viewBinding.invalidateAll()
            }
    }

    class ReceivedMessageViewHolder(val viewBinding: ItemRecievedMessageBinding)
        :RecyclerView.ViewHolder(viewBinding.root){
        fun bind(message: Message){
            viewBinding.message = message
            viewBinding.invalidateAll()
        }
    }
}