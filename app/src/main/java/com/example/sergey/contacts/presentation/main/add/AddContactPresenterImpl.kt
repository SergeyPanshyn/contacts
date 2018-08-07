package com.example.sergey.contacts.presentation.main.add

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.domain.interactor.ContactsInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class AddContactPresenterImpl<T: AddContactPresenter.AddContactView>(private val contactsInteractor: ContactsInteractor): AddContactPresenter<T> {

    private var view: T? = null

    override fun saveContact(contact: Contact) {
        launch(UI) {
            val s = contactsInteractor.saveContact(contact)

            view?.one()
        }
    }

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }

}