package com.example.sergey.contacts.presentation.main.add

class AddContactPresenterImpl<T: AddContactPresenter.AddContactView>(): AddContactPresenter<T> {

    private var view: T? = null

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }

}