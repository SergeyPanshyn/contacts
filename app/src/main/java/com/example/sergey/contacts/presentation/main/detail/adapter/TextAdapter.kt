package com.example.sergey.contacts.presentation.main.detail.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sergey.contacts.R

class TextAdapter(val context: Context, val data: List<String>) : RecyclerView.Adapter<TextViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.detail_text_rv_item, parent, false)

        return TextViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
        holder.textView.text = data[position]
    }
}