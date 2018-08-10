package com.example.sergey.contacts.presentation.main.list.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sergey.contacts.R
import com.example.sergey.contacts.data.db.entity.Contact
import java.util.*

class RvAdapter(val context: Context, val data: List<Contact>, val onClickListener: View.OnClickListener) : RecyclerView.Adapter<RvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)

        return RvViewHolder(itemView, onClickListener)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val current = data[position]
        holder.nameTv.text = context.getString(R.string.full_name, current.firstName, current.lastName)
        holder.userIv.backgroundTintList = ContextCompat.getColorStateList(context, current.iconColor)
    }


}