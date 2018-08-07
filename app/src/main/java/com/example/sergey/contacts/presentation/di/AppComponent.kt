package com.example.sergey.contacts.presentation.di

import com.example.sergey.contacts.data.di.DataModule
import com.example.sergey.contacts.data.di.RepositoriesModule
import com.example.sergey.contacts.presentation.ContactsApp
import com.example.sergey.contacts.presentation.login.di.LoginComponent
import com.example.sergey.contacts.presentation.login.di.LoginModule
import com.example.sergey.contacts.presentation.main.add.di.AddContactComponent
import com.example.sergey.contacts.presentation.main.add.di.AddContactModule
import com.example.sergey.contacts.presentation.main.list.di.ListComponent
import com.example.sergey.contacts.presentation.main.list.di.ListModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, DataModule::class, RepositoriesModule::class))
@Singleton
interface AppComponent {

    fun provideLoginComponent(loginModule: LoginModule): LoginComponent

    fun provideListComponent(listModule: ListModule): ListComponent

    fun provideAddContactComponent(addContactModule: AddContactModule): AddContactComponent

    fun inject(contactsApp: ContactsApp)

}