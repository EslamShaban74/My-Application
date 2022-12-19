package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.myapplication.adapter.PageViewAdapter
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.calculate_age.*
import kotlinx.android.synthetic.main.fragment_chat.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolBar.title = "Hello"
        setSupportActionBar(toolBar)

        val myAdapter = PageViewAdapter(supportFragmentManager)
        viewPager.adapter = myAdapter

        tabLayout.setupWithViewPager(viewPager)

       val actionBarDrawerToggle = ActionBarDrawerToggle(this,drawerLayout ,toolBar,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        navView.setNavigationItemSelectedListener(this)
//        tv.animate()
//            .alpha(0.5f)
//            .scaleXBy(0.5f)
//            .scaleYBy(0.5f)
//            .rotation(360f)
//            .translationY(20f)
//            .duration=2000
//
//
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main_menu,menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        Toast.makeText(this, item.title.toString(),Toast.LENGTH_LONG).show()
//        return true
//    }
//
//
//    fun press(view: View) {
//        if (view == tv) {
//            Toast.makeText(this, "Hello text view", Toast.LENGTH_LONG).show()
//        } else {
//            startActivity(Intent(this, CalculateAge::class.java))
//        }
//    }
//

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this,item.title.toString(),Toast.LENGTH_LONG).show()
        drawerLayout.closeDrawer(GravityCompat.START)
        return  true
    }

    override fun onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }
}