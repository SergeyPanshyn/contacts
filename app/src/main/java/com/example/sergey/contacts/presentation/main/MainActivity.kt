package com.example.sergey.contacts.presentation.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.support.v7.app.AppCompatActivity
import butterknife.ButterKnife
import com.example.sergey.contacts.R
import com.example.sergey.contacts.presentation.ContactsApp
import com.example.sergey.contacts.presentation.main.add.di.AddContactModule
import com.example.sergey.contacts.presentation.main.list.di.ListModule

class MainActivity: AppCompatActivity() {

    companion object {
        const val ADD_CONTACT_FRAGMENT_TAG = "AddContactFragmentTag"
        const val LIST_FRAGMENT_TAG = "ListFragmentTag"
    }

    val listComponent by lazy { ContactsApp.appComponent?.provideListComponent(ListModule()) }

    val appCompatComponent by lazy { ContactsApp.appComponent?.provideAddContactComponent(AddContactModule()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        ButterKnife.bind(this)

        if (savedInstanceState == null) {
            setupFragment()
        }

    }

    private fun setupFragment() {
        replaceFragment(ListFragment(), LIST_FRAGMENT_TAG)
    }

    private fun replaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container_fl, fragment, tag)

        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

}