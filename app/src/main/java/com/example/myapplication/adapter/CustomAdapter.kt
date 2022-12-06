package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.myapplication.R
import com.example.myapplication.model.Fruits
import kotlinx.android.synthetic.main.custom_adapter.view.*

class CustomAdapter( context: Context,  fruits: ArrayList<Fruits>) :
    ArrayAdapter<Fruits>(context, R.layout.custom_adapter, fruits){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val listViewItem = LayoutInflater.from(context).inflate(R.layout.custom_adapter,parent,false)
        val fruits = getItem(position)

        listViewItem.textView2.text = fruits!!.name
        listViewItem.textView3.text = fruits!!.desc
        listViewItem.imageView.setImageResource(fruits!!.image)


        return  listViewItem
    }
    }