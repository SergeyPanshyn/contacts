package com.example.sergey.contacts.presentation.main.detail

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.domain.interactor.ContactsInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class DetailPresenterImpl<T : DetailPresenter.DetailView>(val contactsInteractor: ContactsInteractor) : DetailPresenter<T> {

    private var view: T? = null

    override fun deleteContact(contact: Contact) {
        launch(UI) {
            contactsInteractor.deleteContact(contact)

            view?.onContactDeleted()
        }
    }

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        view = null
    }
}