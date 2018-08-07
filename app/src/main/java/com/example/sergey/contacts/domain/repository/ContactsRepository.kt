package com.example.sergey.contacts.domain.repository

import com.example.sergey.contacts.data.db.entity.Contact

interface ContactsRepository {

    suspend fun saveContact(contact: Contact)

    suspend fun getContacts(): List<Contact>
}