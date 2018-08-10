package com.example.sergey.contacts.presentation.main.list.di

import com.example.sergey.contacts.domain.repository.ContactsRepository
import com.example.sergey.contacts.domain.interactor.ContactsInteractor
import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.list.ListPresenter
import com.example.sergey.contacts.presentation.main.list.ListPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @Provides
    @PerActivity
    internal fun provideListPresenter(contactsInteractor: ContactsInteractor): ListPresenter<ListPresenter.ListView> = ListPresenterImpl(contactsInteractor)

    @Provides
    @PerActivity
    internal fun provideMainInteractor(contactsRepository: ContactsRepository) = ContactsInteractor(contactsRepository)

}