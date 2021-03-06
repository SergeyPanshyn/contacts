package com.example.sergey.contacts.data.db.entity

import android.arch.persistence.room.*
import com.example.sergey.contacts.data.db.converter.ListConverter

@Entity(tableName = "contact")
data class Contact(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        val firstName: String,
        val lastName: String,
        @TypeConverters(ListConverter::class)
        var phonesList: List<String>? = null,
        @TypeConverters(ListConverter::class)
        var emailsList: List<String>? = null,
        val iconColor: Int
)