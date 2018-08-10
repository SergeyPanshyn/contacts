package com.example.sergey.contacts.presentation.main.edit

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.presentation.Presenter

interface AddContactPresenter<T> : Presenter<T> {

    interface AddContactView {

        fun onContactCreated()

        fun onContactUpdated(contact: Contact)

    }

    fun saveContact(contact: Contact)

    fun updateContact(contact: Contact)

}