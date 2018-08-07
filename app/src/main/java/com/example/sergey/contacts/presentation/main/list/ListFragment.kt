package com.example.sergey.contacts.presentation.main.list

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.sergey.contacts.R
import com.example.sergey.contacts.presentation.main.MainActivity
import javax.inject.Inject

class ListFragment: Fragment(), ListPresenter.ListView {

    @Inject
    lateinit var listPresenter: ListPresenter<ListPresenter.ListView>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.list_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).listComponent?.inject(this)
        listPresenter.setView(this)
    }

}