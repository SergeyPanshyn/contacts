package com.example.sergey.contacts.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.sergey.contacts.data.db.dao.ContactsDao
import com.example.sergey.contacts.data.db.entity.Contact

@Database(entities = arrayOf(Contact::class), version = 1, exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

}