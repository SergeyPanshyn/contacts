package com.example.sergey.contacts.presentation.main.list.adapter

import android.support.v7.widget.RecyclerView
import android.view.TextureView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sergey.contacts.R

class RvViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.contact_item_name_tv)
    lateinit var nameTv: TextView

    @BindView(R.id.contact_item_phone_tv)
    lateinit var phoneTv: TextView

    @BindView(R.id.contact_item_email_tv)
    lateinit var emailTv: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

}