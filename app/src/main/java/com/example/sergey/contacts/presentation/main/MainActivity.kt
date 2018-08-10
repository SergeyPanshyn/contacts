package com.example.sergey.contacts.presentation.main

import android.Manifest
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.ButterKnife
import com.example.sergey.contacts.R
import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.data.entity.ContactParcelable
import com.example.sergey.contacts.presentation.ContactsApp
import com.example.sergey.contacts.presentation.main.edit.AddContactFragment
import com.example.sergey.contacts.presentation.main.edit.di.AddContactModule
import com.example.sergey.contacts.presentation.main.detail.DetailFragment
import com.example.sergey.contacts.presentation.main.detail.di.DetailModule
import com.example.sergey.contacts.presentation.main.list.ListFragment
import com.example.sergey.contacts.presentation.main.list.di.ListModule
import com.tbruyelle.rxpermissions.RxPermissions

class MainActivity : AppCompatActivity(),
        ListFragment.ListListener,
        AddContactFragment.AddContactListener,
        DetailFragment.DetailListener {

    companion object {
        const val ADD_CONTACT_FRAGMENT_TAG = "AddContactFragmentTag"
        const val LIST_FRAGMENT_TAG = "ListFragmentTag"
        const val DETAIL_FRAGMENT_TAG = "DetailFragmentTag"
    }

    val listComponent by lazy { ContactsApp.appComponent?.provideListComponent(ListModule()) }

    val addContactComponent by lazy { ContactsApp.appComponent?.provideAddContactComponent(AddContactModule()) }

    val detailComponent by lazy { ContactsApp.appComponent?.provideDetailComponent(DetailModule()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        ButterKnife.bind(this)

        setupPermissionsRequest()

        if (savedInstanceState == null) {
            setupFragment()
        }

    }

    private fun setupPermissionsRequest() {
        val rxPermissions = RxPermissions(this)
        rxPermissions
                .request(Manifest.permission.CALL_PHONE)
                .subscribe {
                    if (!it) {
                        Toast.makeText(this, "This permission is needed to make calls from the application", Toast.LENGTH_SHORT).show()
                    }
                }
    }

    private fun setupFragment() {
        replaceFragment(ListFragment(), LIST_FRAGMENT_TAG)
    }

    override fun onAddContactClick() {
        replaceFragment(AddContactFragment(), ADD_CONTACT_FRAGMENT_TAG, true)
    }

    override fun onListItemClick(contact: Contact) {
        replaceFragment(DetailFragment.newInstance(
                ContactParcelable(
                        contact.id!!,
                        contact.firstName,
                        contact.lastName,
                        contact.phonesList,
                        contact.emailsList
                )
        ), DETAIL_FRAGMENT_TAG, true)
    }

    override fun onContactAdded() {
        supportFragmentManager.popBackStack()
    }

    override fun onContactUpdated(contact: Contact) {
        (0 until supportFragmentManager.backStackEntryCount).forEach {
            supportFragmentManager.popBackStack()
        }
        replaceFragment(DetailFragment.newInstance(
                ContactParcelable(
                        contact.id!!,
                        contact.firstName,
                        contact.lastName,
                        contact.phonesList,
                        contact.emailsList
                )
        ), DETAIL_FRAGMENT_TAG, true)
    }

    override fun onContactDeleted() {
        supportFragmentManager.popBackStack()
    }

    override fun onEditContactClick(contactParcelable: ContactParcelable) {
        replaceFragment(AddContactFragment.newInstance(contactParcelable), ADD_CONTACT_FRAGMENT_TAG, true)
    }

    private fun replaceFragment(fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container_fl, fragment, tag)

        if (addToBackStack) transaction.addToBackStack(null)
        transaction.commit()
    }

}