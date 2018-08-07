package com.example.sergey.contacts.presentation.main.add

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.sergey.contacts.R
import com.example.sergey.contacts.presentation.main.MainActivity
import javax.inject.Inject

class AddContactFragment: Fragment(), AddContactPresenter.AddContactView {

    @Inject
    lateinit var addContactPresenter: AddContactPresenter<AddContactPresenter.AddContactView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.add_contact_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).addContactComponent?.inject(this)
        addContactPresenter.setView(this)
    }


}