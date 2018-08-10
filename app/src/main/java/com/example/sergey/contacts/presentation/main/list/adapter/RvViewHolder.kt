package com.example.sergey.contacts.presentation.main.list.adapter

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sergey.contacts.R

class RvViewHolder(itemView: View,
                   val onClickListener: View.OnClickListener) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.contact_item_cv)
    lateinit var contactCv: CardView

    @BindView(R.id.contact_item_name_tv)
    lateinit var nameTv: TextView

    @BindView(R.id.contact_item_user_iv)
    lateinit var userIv: ImageView

    init {
        ButterKnife.bind(this, itemView)

        contactCv.setOnClickListener {
            it.tag = adapterPosition
            onClickListener.onClick(it)
        }
    }

}