package com.example.sergey.contacts.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.example.sergey.contacts.data.db.entity.Contact

@Dao
interface ContactsDao {

    @Query("SELECT * FROM contact")
    fun getContacts(): List<Contact>

    @Insert
    fun saveContacts(contact: List<Contact>)

    @Insert
    fun saveContact(contact: Contact)

    @Update
    fun updateContact(contact: Contact)

    @Query("DELETE FROM contact")
    fun clearContacts()

}