package com.example.sergey.contacts.presentation.main.add.di

import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.add.AddContactPresenter
import com.example.sergey.contacts.presentation.main.add.AddContactPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class AddContactModule {

    @Provides
    @PerActivity
    internal fun provideAddContactPresenter(): AddContactPresenter<AddContactPresenter.AddContactView> = AddContactPresenterImpl()

}