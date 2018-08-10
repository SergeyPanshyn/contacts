package com.example.sergey.contacts.presentation.main.detail.di

import com.example.sergey.contacts.domain.interactor.ContactsInteractor
import com.example.sergey.contacts.domain.repository.ContactsRepository
import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.detail.DetailPresenter
import com.example.sergey.contacts.presentation.main.detail.DetailPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @Provides
    @PerActivity
    internal fun provideDetailPresenter(contactsInteractor: ContactsInteractor): DetailPresenter<DetailPresenter.DetailView> = DetailPresenterImpl(contactsInteractor)

    @Provides
    @PerActivity
    internal fun provideMainInteractor(contactsRepository: ContactsRepository) = ContactsInteractor(contactsRepository)

}