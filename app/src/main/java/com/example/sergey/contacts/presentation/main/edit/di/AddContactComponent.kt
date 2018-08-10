package com.example.sergey.contacts.presentation.main.edit.di

import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.edit.AddContactFragment
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(AddContactModule::class))
interface AddContactComponent {

    fun inject(addContactFragment: AddContactFragment)

}