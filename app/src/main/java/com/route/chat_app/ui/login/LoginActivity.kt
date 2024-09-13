package com.route.chat_app.ui.login


import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.route.chat_app.R
import com.route.chat_app.base.BaseActivity
import com.route.chat_app.databinding.ActivityLoginBinding
import com.route.chat_app.ui.home.HomeActivity
import com.route.chat_app.ui.register.RegisterActivity


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {
    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun generateViewModel(): LoginViewModel {
        return ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding.vm = viewModel
        viewModel.navigator = this

    }

    override fun gotoRegister() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun gotoHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }


}