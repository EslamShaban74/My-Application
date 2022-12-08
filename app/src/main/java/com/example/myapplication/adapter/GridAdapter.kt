package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import com.example.myapplication.R
import kotlinx.android.synthetic.main.grid_item.view.*

class GridAdapter(private val context : Context,val images : Array<Int>) : BaseAdapter() {
    override fun getCount(): Int {
        return    images.size
    }

    override fun getItem(p0: Int): Any {
        TODO("Not yet implemented")
    }


    override fun getItemId(position: Int): Long {
        return  images[position].toLong()
    }

    override fun getView(position: Int, p1: View?, parent: ViewGroup?): View {
       val itemView =  LayoutInflater.from(context).inflate(R.layout.grid_item,parent, false )
        itemView.imageView.setImageResource(images[position])
        return  itemView
    }
}