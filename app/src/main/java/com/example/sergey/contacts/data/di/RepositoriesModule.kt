package com.example.sergey.contacts.data.di

import com.example.sergey.contacts.data.db.dao.ContactsDao
import com.example.sergey.contacts.domain.repository.ContactsRepository
import com.example.sergey.contacts.data.repository.ContactsRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideContactsRepository(contactsDao: ContactsDao): ContactsRepository = ContactsRepositoryImpl(contactsDao)

}