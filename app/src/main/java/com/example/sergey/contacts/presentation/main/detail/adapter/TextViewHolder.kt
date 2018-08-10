package com.example.sergey.contacts.presentation.main.detail.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sergey.contacts.R

class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.detail_text_rv_item_main_tv)
    lateinit var textView: TextView

    init {
        ButterKnife.bind(this, itemView)
    }

}