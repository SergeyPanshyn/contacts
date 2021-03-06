package com.example.sergey.contacts.presentation.di

import com.example.sergey.contacts.data.di.DataModule
import com.example.sergey.contacts.data.di.RepositoriesModule
import com.example.sergey.contacts.presentation.ContactsApp
import com.example.sergey.contacts.presentation.main.edit.di.AddContactComponent
import com.example.sergey.contacts.presentation.main.edit.di.AddContactModule
import com.example.sergey.contacts.presentation.main.detail.di.DetailComponent
import com.example.sergey.contacts.presentation.main.detail.di.DetailModule
import com.example.sergey.contacts.presentation.main.list.di.ListComponent
import com.example.sergey.contacts.presentation.main.list.di.ListModule
import dagger.Component
import javax.inject.Singleton

@Component(modules = arrayOf(AppModule::class, DataModule::class, RepositoriesModule::class))
@Singleton
interface AppComponent {

    fun provideListComponent(listModule: ListModule): ListComponent

    fun provideAddContactComponent(addContactModule: AddContactModule): AddContactComponent

    fun provideDetailComponent(detailModule: DetailModule): DetailComponent

    fun inject(contactsApp: ContactsApp)

}