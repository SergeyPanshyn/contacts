package com.example.sergey.contacts.domain.interactor

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.domain.repository.ContactsRepository

class ContactsInteractor(
        private val contactsRepository: ContactsRepository
) {

    suspend fun loadData() = contactsRepository.getContacts()

    suspend fun saveContact(contact: Contact) = contactsRepository.saveContact(contact)

}