package com.example.sergey.contacts.data.db.entity

import android.arch.persistence.room.*
import com.example.sergey.contacts.data.db.converter.ListConverter

@Entity(tableName = "contact")
data class Contact(
        @PrimaryKey(autoGenerate = true)
        val id: Long? = null,
        val firstName: String,
        val lastName: String
)