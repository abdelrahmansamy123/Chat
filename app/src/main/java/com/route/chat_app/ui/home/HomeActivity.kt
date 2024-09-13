package com.route.chat_app.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chat_app.Constants
import com.route.chat_app.R
import com.route.chat_app.base.BaseActivity
import com.route.chat_app.database.models.Room
import com.route.chat_app.databinding.ActivityHomeBinding
import com.route.chat_app.ui.addRoom.AddRoomActivity
import com.route.chat_app.ui.chat.ChatRoom


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        initializeAdapter()
        subscribeToLiveData()
        viewModel.loadRooms()

    }

    private fun subscribeToLiveData() {
        viewModel.roomsLiveData.observe(this) {
            adapter.changeData(it)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.loadRooms()
    }

    val adapter = RoomsAdapter()
    private fun initializeAdapter() {
        viewBinding.content.roomsRecycler.adapter = adapter
        adapter.onItemClickListener = object : RoomsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int, room: Room) {
                val intent = Intent(this@HomeActivity, ChatRoom::class.java)
                intent.putExtra(Constants.EXTRA_ROOM, room)
                startActivity(intent)
            }
        }
    }

    override fun generateViewModel(): HomeViewModel {
        return ViewModelProvider(this)
            .get(HomeViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun openAddRoom() {
        val intent = Intent(this, AddRoomActivity::class.java)
        startActivity(intent)
    }
}

