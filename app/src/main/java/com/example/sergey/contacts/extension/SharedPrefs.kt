package com.example.sergey.contacts.extension

import android.content.Context
import android.preference.PreferenceManager
import com.example.sergey.contacts.R

fun Context.setLoggedIn() {
    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    prefs.edit().putBoolean(getString(R.string.logged_in), true).apply()
}

fun Context.isLoggedIn(): Boolean {
    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    return prefs.getBoolean(getString(R.string.logged_in), false)
}

fun Context.clearPrefs() {
    val prefs = PreferenceManager.getDefaultSharedPreferences(this)
    prefs.edit().clear().apply()
}