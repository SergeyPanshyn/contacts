package com.example.sergey.contacts.extension

import com.example.sergey.contacts.R
import java.util.*

fun getRandomColor(): Int {
    return when(Random().nextInt(10)) {
        0 -> R.color.tintColor0
        1 -> R.color.tintColor1
        2 -> R.color.tintColor2
        3 -> R.color.tintColor3
        4 -> R.color.tintColor4
        5 -> R.color.tintColor5
        6 -> R.color.tintColor6
        7 -> R.color.tintColor7
        8 -> R.color.tintColor8
        9 -> R.color.tintColor9
        else -> R.color.tintColor0
    }
}