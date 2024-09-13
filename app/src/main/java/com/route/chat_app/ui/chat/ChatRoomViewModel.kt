package com.route.chat_app.ui.chat

import androidx.databinding.ObservableField
import com.google.firebase.Timestamp
import com.route.chat_app.UserProvider
import com.route.chat_app.base.BaseNavigator
import com.route.chat_app.base.BaseViewModel
import com.route.chat_app.database.FireStoreUtils
import com.route.chat_app.database.models.Message
import com.route.chat_app.database.models.Room

interface Navigator : BaseNavigator {
    fun back()
}

class ChatRoomViewModel : BaseViewModel<Navigator>() {
    var room: Room? = null
    val messageField = ObservableField<String>()

    fun sendMessage() {
        if (messageField.get().isNullOrBlank()) return
        val message = Message(
            content = messageField.get(),
            senderName = UserProvider.user?.userName,
            senderId = UserProvider.user?.id,
            roomId = room?.id,
            dateTime = Timestamp.now()
        )
        FireStoreUtils().sendMessage(message).addOnCompleteListener {
            if (it.isSuccessful) {
                messageField.set("")
                return@addOnCompleteListener
            }
            navigator?.showMessage("error sending your message",
                posActionTitle = "try Again",
                posAction = {
                    sendMessage()
                })

        }
    }
}