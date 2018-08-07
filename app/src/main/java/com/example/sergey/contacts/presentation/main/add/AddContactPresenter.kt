package com.example.sergey.contacts.presentation.main.add

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.presentation.Presenter

interface AddContactPresenter<T>: Presenter<T> {

    interface AddContactView {

    }

    fun saveContact(contact: Contact)

}