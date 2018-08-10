package com.example.sergey.contacts.presentation.main.edit.di

import com.example.sergey.contacts.domain.interactor.ContactsInteractor
import com.example.sergey.contacts.domain.repository.ContactsRepository
import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.edit.AddContactPresenter
import com.example.sergey.contacts.presentation.main.edit.AddContactPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class AddContactModule {

    @Provides
    @PerActivity
    internal fun provideAddContactPresenter(contactsInteractor: ContactsInteractor): AddContactPresenter<AddContactPresenter.AddContactView> = AddContactPresenterImpl(contactsInteractor)

    @Provides
    @PerActivity
    internal fun provideContactsInteractor(contactsRepository: ContactsRepository) = ContactsInteractor(contactsRepository)

}