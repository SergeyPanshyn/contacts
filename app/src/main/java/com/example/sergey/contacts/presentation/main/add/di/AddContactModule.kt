package com.example.sergey.contacts.presentation.main.add.di

import com.example.sergey.contacts.domain.interactor.ContactsInteractor
import com.example.sergey.contacts.domain.repository.ContactsRepository
import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.add.AddContactPresenter
import com.example.sergey.contacts.presentation.main.add.AddContactPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class AddContactModule {

    @Provides
    @PerActivity
    internal fun provideAddContactPresenter(contactsInteractor: ContactsInteractor): AddContactPresenter<AddContactPresenter.AddContactView>
            = AddContactPresenterImpl(contactsInteractor)

    @Provides
    @PerActivity
    internal fun provideContactsInteractor(contactsRepository: ContactsRepository) = ContactsInteractor(contactsRepository)

}