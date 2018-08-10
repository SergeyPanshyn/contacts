package com.example.sergey.contacts.presentation.main.detail.di

import com.example.sergey.contacts.presentation.di.PerActivity
import com.example.sergey.contacts.presentation.main.detail.DetailFragment
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(DetailModule::class))
interface DetailComponent {

    fun inject(detailFragment: DetailFragment)

}