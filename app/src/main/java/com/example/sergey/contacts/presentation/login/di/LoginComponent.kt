package com.example.sergey.contacts.presentation.login.di

import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.login.LoginActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(LoginModule::class))
interface LoginComponent {

    fun inject(loginActivity: LoginActivity)

}