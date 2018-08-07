package com.example.sergey.contacts.presentation

import android.app.Application
import com.example.sergey.contacts.presentation.di.AppComponent
import com.example.sergey.contacts.presentation.di.AppModule
import com.example.sergey.contacts.presentation.di.DaggerAppComponent

class ContactsApp: Application() {

    companion object {
        var appComponent: AppComponent? = null
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        appComponent?.inject(this)
    }
}