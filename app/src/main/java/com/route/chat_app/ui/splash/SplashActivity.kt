package com.route.chat_app.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.route.chat_app.R
import com.route.chat_app.UserProvider
import com.route.chat_app.database.FireStoreUtils
import com.route.chat_app.database.models.User
import com.route.chat_app.ui.home.HomeActivity
import com.route.chat_app.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

    }

    override fun onStart() {
        super.onStart()
        Handler(Looper.getMainLooper())
            .postDelayed({
                         navigate()
            }, 2000)
    }

    fun navigate(){
        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null){
            gotoLogin()
            return
        }
        FireStoreUtils()
            .getUserFromDatabase(auth.currentUser?.uid?:"")
            .addOnCompleteListener {
                if (!it.isSuccessful){
                    gotoLogin()
                    return@addOnCompleteListener
                }
                val user =  it.result.toObject(User::class.java)
                UserProvider.user = user
                gotoHome()
            }
    }
    fun gotoLogin(){
        val intent =Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    fun gotoHome(){
        val intent =Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}