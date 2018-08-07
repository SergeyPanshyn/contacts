package com.example.sergey.contacts.presentation.main.list.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sergey.contacts.R
import com.example.sergey.contacts.data.db.entity.Contact

class RvAdapter(val context: Context, val data: List<Contact>): RecyclerView.Adapter<RvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val itemView= LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)

        return RvViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val current = data[position]
        holder.nameTv.text = "${current.firstName} ${current.lastName}"
        holder.emailTv.text = "jdfhjakfd@top.com"
        holder.phoneTv.text = "+4354612789"
    }
}