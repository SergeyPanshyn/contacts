package com.example.sergey.contacts.presentation.main.add.di

import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.add.AddContactFragment
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(AddContactModule::class))
interface AddContactComponent {

    fun inject(addContactFragment: AddContactFragment)

}