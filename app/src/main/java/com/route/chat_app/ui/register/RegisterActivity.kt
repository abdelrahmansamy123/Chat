package com.route.chat_app.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chat_app.R
import com.route.chat_app.base.BaseActivity
import com.route.chat_app.databinding.ActivityRegisterBinding
import com.route.chat_app.ui.home.HomeActivity
import com.route.chat_app.ui.login.LoginActivity

class RegisterActivity :
    BaseActivity<ActivityRegisterBinding, RegisterViewModel>(),
    RegisterNavigator {
    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    override fun generateViewModel(): RegisterViewModel {
        return ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this
    }

    override fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun gotoLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    //abdu sami
    //abdelrahmansamy123@gmail.com
    //123123
    //123123

}