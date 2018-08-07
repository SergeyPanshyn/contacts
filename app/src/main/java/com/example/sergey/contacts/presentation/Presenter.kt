package com.example.sergey.contacts.presentation

interface Presenter<T> {

    fun setView(view: T)

    fun destroy()

}