package com.example.sergey.contacts.presentation.main.list

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.domain.interactor.ContactsInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class ListPresenterImpl<T: ListPresenter.ListView>(private val contactsInteractor: ContactsInteractor): ListPresenter<T> {

    private var view: T? = null

    override fun loadContacts() {
        launch(UI) {
            val contacts = contactsInteractor.loadData()

            view?.showContacts(contacts)
        }
    }

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }
}