package com.example.sergey.contacts.data.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverter
import android.arch.persistence.room.TypeConverters
import com.example.sergey.contacts.data.db.converter.ListConverter

@Entity(tableName = "contact")
data class Contact(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        val firstName: String,
        val lastName: String,
        @TypeConverters(ListConverter::class)
        val emailList: List<String>? = null,
        @TypeConverters(ListConverter::class)
        val phoneList: List<String>? = null
)