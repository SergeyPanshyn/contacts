package com.example.sergey.contacts.data.entity.enum

import android.text.InputType

enum class EditTextInput(val inputType: Int) {
    EMAIL(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS),
    PHONE(InputType.TYPE_CLASS_PHONE)
}