package com.rabea.mycontacts

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.rabea.mycontacts.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        sharedPreferences = getSharedPreferences(Constants.sharedPreferenceName, MODE_PRIVATE)

        binding.btnLogin.setOnClickListener {

//            if (binding.etUserName.equals("rabea")){
            if (binding.etUserName.text.toString() == "rabea"){
                if (binding.etPassword.text.toString() == "1234"){

                    sharedPreferences.edit().putBoolean(Constants.isLoggedIn, true).apply()
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()

                } else{
                    Toast.makeText(this, "password incorrect!", Toast.LENGTH_SHORT).show()
                }
            } else{
                Toast.makeText(this, "username incorrect!", Toast.LENGTH_SHORT).show()
            }

        }

    }
}




