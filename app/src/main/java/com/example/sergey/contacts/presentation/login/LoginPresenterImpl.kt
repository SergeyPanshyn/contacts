package com.example.sergey.contacts.presentation.login

import android.content.Context

class LoginPresenterImpl<T : LoginPresenter.LoginView>() : LoginPresenter<T> {

    private var view: T? = null

    override fun setView(view: T) {
        this.view = view
    }

    override fun destroy() {
        this.view = null
    }
}