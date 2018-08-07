package com.example.sergey.contacts.data.db.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {

        @TypeConverter
        fun fromString(value: String): ArrayList<String> {
            val listType = object : TypeToken<List<String>>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromList(list: List<String>): String {
            val gson = Gson()
            return gson.toJson(list)
        }

}