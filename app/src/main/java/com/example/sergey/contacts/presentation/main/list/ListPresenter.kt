package com.example.sergey.contacts.presentation.main.list

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.presentation.Presenter

interface ListPresenter<T>: Presenter<T> {

    interface ListView {

        fun showContacts(contacts: List<Contact>)

    }

    fun loadContacts()

}