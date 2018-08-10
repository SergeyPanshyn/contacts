package com.example.sergey.contacts.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.sergey.contacts.data.db.converter.ListConverter
import com.example.sergey.contacts.data.db.dao.ContactsDao
import com.example.sergey.contacts.data.db.entity.Contact

@Database(entities = arrayOf(Contact::class), version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

}