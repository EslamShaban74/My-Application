package com.example.myapplication.adapter

import android.content.Context
import android.os.CancellationSignal.OnCancelListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.Fruits
import kotlinx.android.synthetic.main.custom_adapter.view.*


class CustomAdapter(private val context: Context, private val fruits: ArrayList<Fruits>) :RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView2: TextView = itemView.textView2
        val textView3: TextView = itemView.textView3
        val imageView: ImageView = itemView.imageView
        val add: ImageView = itemView.add
        val remove: ImageView = itemView.remove
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val listViewItem = LayoutInflater.from(parent.context).inflate(R.layout.custom_adapter,parent,false)
        return MyViewHolder(listViewItem)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val  fruits = fruits[position]
        holder.textView2.text = fruits.name
        holder.textView3.text = fruits.desc
        holder.imageView.setImageResource(fruits.image)


        holder.itemView.setOnClickListener{
            Toast.makeText(context,"${holder.textView2.text}",Toast.LENGTH_LONG).show()
        }

        holder.add.setOnClickListener {
            addFruit(position,fruits)
        }

        holder.remove.setOnClickListener {
            removeFruits(position)

        }

    }

    private fun removeFruits(position: Int) {
        fruits.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position,fruits.size)

    }

    private fun addFruit(position: Int, fruit: Fruits) {
        fruits.add(position,fruit)
        notifyItemInserted(position)
        notifyItemRangeChanged(position,fruits.size)
    }

    override fun getItemCount(): Int {
        return  fruits.size
    }

}


//class CustomAdapter( context: Context,  fruits: ArrayList<Fruits>) : 
//    ArrayAdapter<Fruits>(context,0, fruits){
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val listViewItem = LayoutInflater.from(context).inflate(R.layout.custom_adapter,parent,false)
//        val fruits = getItem(position)
//
//        listViewItem.textView2.text = fruits!!.name
//        listViewItem.textView3.text = fruits!!.desc
//        listViewItem.imageView.setImageResource(fruits!!.image)
//
//
//        return  listViewItem
//    }
//    }