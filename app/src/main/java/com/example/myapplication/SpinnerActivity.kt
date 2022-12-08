package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_spinner.*

class SpinnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spinner)
        val days = arrayOf("saturday","sunday","monday","tuesday","wednesday","thursday","friday")
        val myAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,days)
        spinner.adapter = myAdapter
    }
}