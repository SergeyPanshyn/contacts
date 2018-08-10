package com.example.sergey.contacts.presentation.main.edit

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.domain.interactor.ContactsInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class AddContactPresenterImpl<T : AddContactPresenter.AddContactView>(private val contactsInteractor: ContactsInteractor) : AddContactPresenter<T> {

    private var view: T? = null

    override fun saveContact(contact: Contact) {
        launch(UI) {
            contactsInteractor.saveContact(contact)

            view?.onContactCreated()
        }
    }

    override fun updateContact(contact: Contact) {
        launch(UI) {
            contactsInteractor.updateContact(contact)

            view?.onContactUpdated(contact)
        }
    }

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }

}