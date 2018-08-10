package com.example.sergey.contacts.presentation.main.edit.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sergey.contacts.R

class EditTextRvViewHolder(itemView: View, val onTextChanged: (text: String, position: Int) -> Unit) : RecyclerView.ViewHolder(itemView) {

    @BindView(R.id.edit_text_rv_item_et)
    lateinit var editText: EditText

    init {
        ButterKnife.bind(this, itemView)
    }

}