package com.route.chat_app.ui.addRoom

import androidx.databinding.ObservableField
import com.route.chat_app.UserProvider
import com.route.chat_app.base.BaseNavigator
import com.route.chat_app.base.BaseViewModel
import com.route.chat_app.database.FireStoreUtils
import com.route.chat_app.database.models.Room

interface Navigator : BaseNavigator {
    fun back()
}

class AddRoomViewModel : BaseViewModel<Navigator>() {
    var selectedCategory: RoomCategory = RoomCategory.getCategories()[0]
    val title = ObservableField<String>()
    val titleError = ObservableField<String>()
    val description = ObservableField<String>()
    val descriptionError = ObservableField<String>()

    fun createRoom() {
        if (!validateForm()) return
        val room = Room(
            title = title.get(),
            description = description.get(),
            categoryId = selectedCategory.id,
            createdBy = UserProvider.user?.id
        )
        navigator?.showLoading("loading...")
        FireStoreUtils().insertRoom(room).addOnCompleteListener {
            navigator?.hideDialoge()
            if (!it.isSuccessful) {
                navigator?.showMessage(it.exception?.localizedMessage ?: "")
                return@addOnCompleteListener
            }
            navigator?.showMessage("Room Created Successfully",
                posActionTitle = "OK",
                posAction = { navigator?.back() })
        }

    }

    fun validateForm(): Boolean {
        var isvalid = true
        if (title.get().isNullOrBlank()) {
            isvalid = false
            titleError.set("please enter room title")
        } else {
            titleError.set(null)
        }
        if (description.get().isNullOrBlank()) {
            isvalid = false
            descriptionError.set("please enter room description")
        } else {
            descriptionError.set(null)
        }
        return isvalid
    }
}