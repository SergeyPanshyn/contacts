package com.example.sergey.contacts.presentation.main.detail

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.presentation.Presenter

interface DetailPresenter<T> : Presenter<T> {

    interface DetailView {

        fun onContactDeleted()

    }

    fun deleteContact(contact: Contact)

}