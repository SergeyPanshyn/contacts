package com.example.sergey.contacts.presentation.main.list

class ListPresenterImpl<T: ListPresenter.ListView>(): ListPresenter<T> {

    private var view: T? = null

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }
}