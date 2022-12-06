package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_web_view.*
import kotlinx.android.synthetic.main.calculate_age.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // btn.setOnClickListener {
        //  Toast.makeText(this,"hello",Toast.LENGTH_LONG).show()
        // }

        tv.animate()
            .alpha(0.5f)
            .scaleXBy(0.5f)
            .scaleYBy(0.5f)
            .rotation(360f)
            .translationY(20f)
            .duration=2000
    }

    fun press(view: View) {
        if (view == tv) {
            Toast.makeText(this, "Hello text view", Toast.LENGTH_LONG).show()
        } else {
            startActivity(Intent(this, CalculateAge::class.java))
        }
    }

    fun openWebsiteInWebView(view: View) {
        // open in web view
        val site = webSiteEditText.text.toString()

        /// to move to another activity you must use category that you added in manifest

        val intent =Intent("com.eslam.web")
        intent.addCategory("DATA")
        intent.putExtra("site",site)
        startActivity(intent)

        /// move to screen and return with data using override method onActivityResult
            //  startActivityForResult(intent,100)
    }

    fun openWebSiteInBrowser(view: View) {
        // open in the browser
        val site = webSiteEditText.text.toString()
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("http://$site")
        startActivity(intent)
    }

    fun goToCameraScreen(view: View) {
        val  intent = Intent(this,CameraActivity::class.java)
        startActivity(intent)
    }

    fun rateApp(view: View) {
        try {

            val uri = Uri.parse("market://details?id $packageName")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            startActivity(intent)
        }catch (e:Exception){

            val uri = Uri.parse("http://play.google.com/store/apps/details?id $packageName")
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = uri
            startActivity(intent)
        }

     }

    fun login(view: View) {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }


}