package com.example.sergey.contacts.data.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.sergey.contacts.data.db.ContactsDatabase
import com.example.sergey.contacts.data.db.dao.ContactsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ContactsDatabase =
            Room.databaseBuilder(context, ContactsDatabase::class.java, "contacts_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()

    @Provides
    @Singleton
    fun provideContactsDao(contactsDatabase: ContactsDatabase): ContactsDao = contactsDatabase.contactsDao()

}