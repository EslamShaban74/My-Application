package com.example.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_logout.*

class LogoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        logout.setOnClickListener {
            val shared = getSharedPreferences("Login", Context.MODE_PRIVATE)
           val sharedPrefs =  shared.edit()
            sharedPrefs.clear().apply()
            finish()
        }
    }
}