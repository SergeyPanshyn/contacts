package com.example.sergey.contacts.presentation.main.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
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
import com.example.sergey.contacts.presentation.main.MainActivity
import com.example.sergey.contacts.presentation.main.list.adapter.RvAdapter
import javax.inject.Inject
import kotlin.properties.Delegates

class ListFragment: Fragment(), ListPresenter.ListView {

    @BindView(R.id.list_fragment_contacts_rv)
    lateinit var recyclerView: RecyclerView

    private var adapter: RvAdapter by Delegates.notNull()

    @Inject
    lateinit var listPresenter: ListPresenter<ListPresenter.ListView>

    val listListener by lazy { context as ListListener }

    interface ListListener {

        fun onAddContactClick()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.list_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).listComponent?.inject(this)
        listPresenter.setView(this)
        listPresenter.loadContacts()
    }

    @OnClick(R.id.list_fragment_fab)
    fun onFabClick() {
        listListener.onAddContactClick()
    }

    override fun showContacts(contacts: List<Contact>) {
        adapter = RvAdapter(context!!, contacts)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }



}