package com.example.sergey.contacts.data.db.dao

import android.arch.persistence.room.*
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

    @Delete
    fun deleteContact(contact: Contact)

}