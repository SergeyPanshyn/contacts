package com.example.sergey.contacts.data.repository

import com.example.sergey.contacts.data.db.dao.ContactsDao
import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.domain.repository.ContactsRepository
import kotlinx.coroutines.experimental.async

class ContactsRepositoryImpl(private val contactsDao: ContactsDao) : ContactsRepository {
    override suspend fun saveContact(contact: Contact) {
        async {
            contactsDao.saveContact(contact)
        }.await()
    }

    override suspend fun getContacts(): List<Contact> {
        return async { contactsDao.getContacts() }.await()
    }

    override suspend fun deleteContact(contact: Contact) {
        async {
            contactsDao.deleteContact(contact)
        }.await()
    }

    override suspend fun updateContact(contact: Contact) {
        async {
            contactsDao.updateContact(contact)
        }.await()
    }
}