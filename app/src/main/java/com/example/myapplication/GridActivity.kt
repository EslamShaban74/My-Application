package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.adapter.GridAdapter
import kotlinx.android.synthetic.main.activity_grid.*

class GridActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        val images = arrayOf(
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
            R.drawable.apple,
        )
        val myAdapter = GridAdapter(this,images)
        gridView.adapter = myAdapter
    }
}