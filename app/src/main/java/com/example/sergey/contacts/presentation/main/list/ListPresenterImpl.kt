package com.example.sergey.contacts.presentation.main.list

import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.data.entity.enum.SortType
import com.example.sergey.contacts.domain.interactor.ContactsInteractor
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class ListPresenterImpl<T : ListPresenter.ListView>(private val contactsInteractor: ContactsInteractor) : ListPresenter<T> {

    private var view: T? = null

    override fun loadContacts() {
        launch(UI) {
            val contacts = contactsInteractor.loadData()

            sortContacts(contacts, SortType.BY_NAME)
        }
    }

    override fun sortContacts(contacts: List<Contact>, sortBy: SortType) {
        launch(UI) {
            val sortedContacts = when (sortBy) {
                SortType.BY_NAME -> {
                    async { contacts.sortedBy { it.firstName } }.await()
                }
                SortType.BY_LAST_NAME -> {
                    async { contacts.sortedBy { it.lastName } }.await()
                }
            }
            view?.showContacts(sortedContacts)
        }
    }

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }
}