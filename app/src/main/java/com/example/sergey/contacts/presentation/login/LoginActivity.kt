package com.example.sergey.contacts.presentation.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.sergey.contacts.R
import com.example.sergey.contacts.presentation.ContactsApp
import com.example.sergey.contacts.presentation.login.di.LoginModule
import javax.inject.Inject

class LoginActivity : AppCompatActivity(), LoginPresenter.LoginView {

    val loginComponent by lazy { ContactsApp.appComponent?.provideLoginComponent(LoginModule()) }

    @Inject
    lateinit var loginPresenter: LoginPresenter<LoginPresenter.LoginView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        daggerInit()

    }

    private fun daggerInit() {
        loginComponent?.inject(this)
        loginPresenter.setView(this)
    }
}