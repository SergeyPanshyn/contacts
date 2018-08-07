package com.example.sergey.contacts.presentation.di

import android.arch.persistence.room.Room
import android.content.Context
import com.example.sergey.contacts.data.db.ContactsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return context
    }

}