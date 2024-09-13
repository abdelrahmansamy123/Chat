package com.route.chat_app.ui.addRoom


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProvider
import com.route.chat_app.R
import com.route.chat_app.base.BaseActivity
import com.route.chat_app.databinding.ActivityAddRoomBinding
import com.route.chat_app.ui.addRoom.RoomCategory.Companion.getCategories

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding, AddRoomViewModel>(), Navigator {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
        initializeSpinner()
        viewBinding.toolBar.backImage.setOnClickListener {
            finish()
        }
    }

    override fun back() {
        finish()
    }

    lateinit var adapter: CategoriesAdapter
    fun initializeSpinner() {
        adapter = CategoriesAdapter(getCategories())
        viewBinding.categorySpinner.adapter = adapter
        viewBinding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parentViwe: AdapterView<*>?,
                    itemView: View?,
                    position: Int,
                    itemId: Long
                ) {
                    val selectedCategory = adapter.getItem(position) as RoomCategory
                    viewModel.selectedCategory = selectedCategory
                    //  val selectedCategory =  getCategories().get(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
    }

    override fun generateViewModel(): AddRoomViewModel {
        return ViewModelProvider(this).get(AddRoomViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.activity_add_room
}