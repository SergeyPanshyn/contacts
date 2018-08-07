package com.example.sergey.contacts.presentation.main.list.di

import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.list.ListFragment
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ListModule::class))
interface ListComponent {

    fun inject(listFragment: ListFragment)

}