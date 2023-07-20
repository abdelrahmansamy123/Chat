package com.route.chat_app.ui.home

import androidx.lifecycle.MutableLiveData
import com.route.chat_app.base.BaseNavigator
import com.route.chat_app.base.BaseViewModel
import com.route.chat_app.database.FireStoreUtils
import com.route.chat_app.database.models.Room

interface Navigator : BaseNavigator{

    fun openAddRoom(){

    }
}

class HomeViewModel: BaseViewModel<Navigator>() {
    val roomsLiveData = MutableLiveData<List<Room>>()
    fun addRoomAction(){
        navigator?.openAddRoom()
    }
    fun loadRooms(){
        FireStoreUtils()
            .getAllRooms()
            .addOnCompleteListener {
                if (!it.isSuccessful){
                    navigator?.showMessage("error loading rooms")
                    return@addOnCompleteListener
                }
                val rooms = it.result.toObjects(Room::class.java)
                roomsLiveData.value = rooms
            }
    }
}