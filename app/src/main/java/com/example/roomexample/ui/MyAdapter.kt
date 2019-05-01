package com.example.roomexample.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomexample.R
import com.example.roomexample.data.local.Data
import kotlinx.android.synthetic.main.recycler_view_layout.view.*

class MyAdapter(private var data: List<Data>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.dbTextView.text = data[position].dataString
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dbTextView: TextView = itemView.dbTextView
    }

    fun updateData(newData: List<Data>) {
        this.data = newData
    }
}