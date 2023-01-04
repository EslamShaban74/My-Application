package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() ,TextWatcher{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /// to watch email and password edit text is empty or not at the overridden method
        email.addTextChangedListener(this)
        password.addTextChangedListener(this)




        loginBtn.setOnClickListener{

            progressBar.visibility = View.VISIBLE
            val handler = Handler()
            handler.postDelayed(Runnable {
                progressBar.visibility = View.GONE
                val sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
                val sharedPrefs = sharedPreferences.edit()
                sharedPrefs.putString("userName",email.text.toString())
                sharedPrefs.putString("password",password.text.toString())
                sharedPrefs.apply()
                val intent = Intent(this,LogoutActivity::class.java)
                startActivity(intent)
            }, 3000)

        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int){

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val email =email.text.toString()
        val password =password.text.toString()
        loginBtn.isEnabled = email.trim().isNotEmpty() && password.trim().isNotEmpty()
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}