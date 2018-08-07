package com.example.sergey.contacts.presentation.main.add

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.sergey.contacts.R
import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.presentation.main.MainActivity
import javax.inject.Inject

class AddContactFragment: Fragment(), AddContactPresenter.AddContactView {

    @BindView(R.id.add_contact_fragment_first_name_et)
    lateinit var firstNameEt: EditText

    @BindView(R.id.add_contact_fragment_last_name_et)
    lateinit var lastNameEt: EditText

    @Inject
    lateinit var addContactPresenter: AddContactPresenter<AddContactPresenter.AddContactView>

    val addContactListener by lazy { context as AddContactListener }

    interface AddContactListener {

        fun onContactAdded()

    }

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

    @OnClick(R.id.add_contact_fragment_save_button)
    fun onSaveButtonClick() {
        addContactPresenter.saveContact(Contact(null, firstNameEt.text.toString(), lastNameEt.text.toString()))
    }

    override fun one() {
        addContactListener.onContactAdded()
    }


}