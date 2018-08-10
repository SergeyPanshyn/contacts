package com.example.sergey.contacts.presentation.main.edit

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.sergey.contacts.R
import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.data.entity.ContactParcelable
import com.example.sergey.contacts.data.entity.enum.EditTextInput
import com.example.sergey.contacts.presentation.main.MainActivity
import com.example.sergey.contacts.presentation.main.edit.adapter.EditTextRvAdapter
import javax.inject.Inject
import kotlin.properties.Delegates

class AddContactFragment : Fragment(), AddContactPresenter.AddContactView {

    @BindView(R.id.add_contact_fragment_first_name_et)
    lateinit var firstNameEt: EditText

    @BindView(R.id.add_contact_fragment_last_name_et)
    lateinit var lastNameEt: EditText

    @BindView(R.id.add_contact_fragment_phones_rv)
    lateinit var phonesRv: RecyclerView

    @BindView(R.id.add_contact_fragment_emails_rv)
    lateinit var emailsRv: RecyclerView

    companion object {
        fun newInstance(contactParcelable: ContactParcelable): AddContactFragment {
            val args = Bundle()
            args.putParcelable(AddContactFragment::class.java.canonicalName, contactParcelable)

            val pacf = AddContactFragment()
            pacf.arguments = args
            return pacf
        }
    }

    private val contact: ContactParcelable? by lazy { arguments?.getParcelable<ContactParcelable>(AddContactFragment::class.java.canonicalName) }

    @Inject
    lateinit var addContactPresenter: AddContactPresenter<AddContactPresenter.AddContactView>

    val addContactListener by lazy { context as AddContactListener }

    interface AddContactListener {

        fun onContactAdded()

        fun onContactUpdated(contact: Contact)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.add_contact_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        initUi()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).addContactComponent?.inject(this)
        addContactPresenter.setView(this)
    }

    private fun initUi() {
        contact?.let {
            firstNameEt.setText(it.firstName)
            lastNameEt.setText(it.lastName)
            it.emailList?.let { list ->
                localEmailsList.addAll(list)
            }
            it.phoneList?.let { list ->
                localPhonesList.addAll(list)
            }
        }

        initEmailRv()

        initPhonesRv()
    }

    var localEmailsList: ArrayList<String> = ArrayList()
    var localPhonesList: ArrayList<String> = ArrayList()

    private fun initPhonesRv() {
        var adapter: EditTextRvAdapter by Delegates.notNull()

        fun handleSizeChange(text: String, position: Int) {
            if (text.isEmpty()) {
                localPhonesList.removeAt(position)
                adapter.notifyItemRemoved(position)
                return
            }

            if (position >= localPhonesList.size) {
                localPhonesList.add(text)
                adapter.notifyItemChanged(position + 1)
            } else {
                localPhonesList[position] = text
            }
        }

        adapter = EditTextRvAdapter(context!!, localPhonesList, EditTextInput.PHONE)
        { text, position ->
            handleSizeChange(text, position)
        }

        phonesRv.layoutManager = LinearLayoutManager(context)
        phonesRv.adapter = adapter
    }

    private fun initEmailRv() {
        var adapter: EditTextRvAdapter by Delegates.notNull()

        fun handleSizeChange(text: String, position: Int) {
            if (text.isEmpty()) {
                localEmailsList.removeAt(position)
                adapter.notifyItemRemoved(position)
                return
            }

            if (position >= localEmailsList.size) {
                localEmailsList.add(text)
                adapter.notifyItemChanged(position + 1)
            } else {
                localEmailsList[position] = text
            }
        }

        adapter = EditTextRvAdapter(context!!, localEmailsList, EditTextInput.EMAIL
        ) { text, position ->
            handleSizeChange(text, position)
        }

        emailsRv.layoutManager = LinearLayoutManager(context)
        emailsRv.adapter = adapter
    }

    @OnClick(R.id.add_contact_fragment_save_button)
    fun onSaveButtonClick() {
        val firstName = firstNameEt.text.toString()
        val lastName = lastNameEt.text.toString()

        if (firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(context, "First and last names are required", Toast.LENGTH_SHORT).show()
            return
        }

        contact?.let {
            addContactPresenter.updateContact(
                    Contact(
                            it.id,
                            firstName,
                            lastName,
                            localPhonesList,
                            localEmailsList
                    )
            )
        } ?: addContactPresenter.saveContact(
                Contact(
                        null,
                        firstName,
                        lastName,
                        localPhonesList,
                        localEmailsList
                )
        )
    }

    override fun onContactCreated() {
        addContactListener.onContactAdded()
    }

    override fun onContactUpdated(contact: Contact) {
        addContactListener.onContactUpdated(contact)
    }


}