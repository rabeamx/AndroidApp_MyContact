package com.rabea.mycontacts

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        sharedPreferences = getSharedPreferences(Constants.sharedPreferenceName, MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({

            if (sharedPreferences.getBoolean(Constants.isLoggedIn, false)){

                startActivity(Intent(this, MainActivity::class.java))
                finishAffinity()
            } else{

                startActivity(Intent(this, LoginActivity::class.java))
                finishAffinity()
            }

        }, 2000)

    }
}