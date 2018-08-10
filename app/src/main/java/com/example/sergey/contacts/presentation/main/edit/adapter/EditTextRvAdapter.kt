package com.example.sergey.contacts.presentation.main.edit.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.sergey.contacts.R
import com.example.sergey.contacts.data.entity.enum.EditTextInput

class EditTextRvAdapter(val context: Context, val data: List<String>, val editTextInput: EditTextInput, val onTextChanged: (text: String, position: Int) -> Unit) : RecyclerView.Adapter<EditTextRvViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditTextRvViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.edit_text_rv_item, parent, false)

        return EditTextRvViewHolder(itemView, onTextChanged)
    }

    override fun getItemCount(): Int {
        return data.size + 1
    }

    override fun onBindViewHolder(holder: EditTextRvViewHolder, position: Int) {
        holder.editText.inputType = editTextInput.inputType

        if (position != data.size) {
            holder.editText.setText(data[holder.adapterPosition])
        }

        holder.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {
                onTextChanged(charSequence.toString(), holder.adapterPosition)
            }

            override fun afterTextChanged(editable: Editable) {}
        })

    }
}