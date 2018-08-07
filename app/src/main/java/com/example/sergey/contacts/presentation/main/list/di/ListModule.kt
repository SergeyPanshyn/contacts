package com.example.sergey.contacts.presentation.main.list.di

import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.list.ListPresenter
import com.example.sergey.contacts.presentation.main.list.ListPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class ListModule {

    @Provides
    @PerActivity
    internal fun provideListPresenter(): ListPresenter<ListPresenter.ListView> = ListPresenterImpl()

}