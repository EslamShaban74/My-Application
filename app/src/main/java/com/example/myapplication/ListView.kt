package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.CustomAdapter
import com.example.myapplication.model.Fruits
import kotlinx.android.synthetic.main.activity_list_view.*
import kotlinx.android.synthetic.main.custom_adapter.view.*

class ListView : AppCompatActivity() {
    val name = arrayOf("Eslam","Shaban","Eslam","Shaban","Eslam","Shaban","Eslam","Shaban","Eslam","Shaban","Eslam","Shaban","Eslam","Shaban","Eslam","Shaban","Eslam","Shaban",)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val fruitList = ArrayList<Fruits>()
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        fruitList.add(Fruits("Banana","desc1",R.drawable.apple))
        fruitList.add(Fruits("Carrot","desc1",R.drawable.apple))
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        fruitList.add(Fruits("Apple","desc1",R.drawable.apple))
        val myAdapter = CustomAdapter(this,fruitList)
            myRecycler.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)
            myRecycler.adapter = myAdapter


//        listView.setOnItemClickListener { _, view, _, _ ->
//            val v = view.textView2 as TextView
//
//            val  intent = Intent(this,ListViewDetails::class.java)
//            if(v.text == "Carrot"){
//                Toast.makeText(this, "${v.text}", Toast.LENGTH_LONG).show()
//            }else{
//                intent.putExtra("name",v.text)
//                startActivity(intent)
//            }
//
//
//        }
    }
}