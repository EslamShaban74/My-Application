package com.example.myapplication

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_login.*
import java.util.Calendar

class LoginActivity : AppCompatActivity(), TextWatcher {
    private val storage = FirebaseStorage.getInstance().reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /// to watch email and password edit text is empty or not at the overridden method
        email.addTextChangedListener(this)
        password.addTextChangedListener(this)





        loginBtn.setOnClickListener {

            if (email.text.isNotEmpty() && password.text.isNotEmpty()) {
                val auth = FirebaseAuth.getInstance()
                auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnSuccessListener {
                        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener {
                        println(it.message.toString())
                        Toast.makeText(this, it.message.toString(), Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(this, "Email and password can not be empty", Toast.LENGTH_LONG)
                    .show()

            }

        }
        signUp.setOnClickListener {

            if (email.text.isNotEmpty() && password.text.isNotEmpty()) {
                val auth = FirebaseAuth.getInstance()
                auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnSuccessListener {
                        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
                    }.addOnFailureListener {
                        println(it.message.toString())
                        Toast.makeText(this, it.message.toString(), Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(this, "Email and password can not be empty", Toast.LENGTH_LONG)
                    .show()

            }
        }

        uploadImage.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK)
            i.type = "image/*"
            startActivityForResult(i,100)
        }




//            progressBar.visibility = View.VISIBLE
//            val handler = Handler()
//            handler.postDelayed(Runnable {
//                progressBar.visibility = View.GONE
//                val sharedPreferences = getSharedPreferences("Login", Context.MODE_PRIVATE)
//                val sharedPrefs = sharedPreferences.edit()
//                sharedPrefs.putString("userName",email.text.toString())
//                sharedPrefs.putString("password",password.text.toString())
//                sharedPrefs.apply()
//                val intent = Intent(this,LogoutActivity::class.java)
//                startActivity(intent)
//            }, 3000)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 100 && resultCode == RESULT_OK && data != null){
            storage.child(Calendar.getInstance().time.toString()).putFile(data.data!!).addOnSuccessListener {
                Toast.makeText(this,"Success",Toast.LENGTH_LONG).show()
            }.addOnCanceledListener {
                Toast.makeText(this,"Cancelled",Toast.LENGTH_LONG).show()

            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        val email = email.text.toString()
        val password = password.text.toString()
        loginBtn.isEnabled = email.trim().isNotEmpty() && password.trim().isNotEmpty()
        signUp.isEnabled = email.trim().isNotEmpty() && password.trim().isNotEmpty()
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}