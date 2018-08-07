package com.example.sergey.contacts.presentation.login.di

import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.login.LoginPresenter
import com.example.sergey.contacts.presentation.login.LoginPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    @PerActivity
    fun provideLoginPresenter(): LoginPresenter<LoginPresenter.LoginView> = LoginPresenterImpl()


}