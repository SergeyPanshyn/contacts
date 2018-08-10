package com.example.sergey.contacts.presentation.main.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.sergey.contacts.R
import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.data.entity.enum.SortType
import com.example.sergey.contacts.presentation.main.MainActivity
import com.example.sergey.contacts.presentation.main.list.adapter.RvAdapter
import java.util.ArrayList
import javax.inject.Inject
import kotlin.properties.Delegates

class ListFragment : Fragment(), ListPresenter.ListView {

    @BindView(R.id.list_fragment_contacts_rv)
    lateinit var recyclerView: RecyclerView

    private var adapter: RvAdapter by Delegates.notNull()

    @Inject
    lateinit var listPresenter: ListPresenter<ListPresenter.ListView>

    private val listListener by lazy { context as ListListener }

    interface ListListener {

        fun onAddContactClick()

        fun onListItemClick(contact: Contact)

    }

    private val contacts: ArrayList<Contact> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.list_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        initRv()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).listComponent?.inject(this)
        listPresenter.setView(this)
        listPresenter.loadContacts()
    }

    private fun initRv() {
        adapter = RvAdapter(context!!, contacts, View.OnClickListener {
            listListener.onListItemClick(contacts[it.tag as Int])
        })
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    @OnClick(R.id.list_fragment_fab)
    fun onFabClick() {
        listListener.onAddContactClick()
    }

    @OnClick(R.id.list_fragment_first_name_sort_button)
    fun onFirstNameSortClick() {
        listPresenter.sortContacts(contacts, SortType.BY_NAME)
    }

    @OnClick(R.id.list_fragment_last_name_sort_button)
    fun onLastNameSortClick() {
        listPresenter.sortContacts(contacts, SortType.BY_LAST_NAME)
    }

    override fun showContacts(contacts: List<Contact>) {
        this.contacts.clear()
        this.contacts.addAll(contacts)
        adapter.notifyDataSetChanged()
    }

    fun sortContacts(sortBy: SortType) {
        listPresenter.sortContacts(contacts, sortBy)
    }


}