package com.example.sergey.contacts.data.entity

import android.os.Parcel
import android.os.Parcelable


data class ContactParcelable(
        val id: Long? = null,
        val firstName: String,
        val lastName: String,
        val phoneList: List<String>? = null,
        val emailList: List<String>? = null,
        val iconColor: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Long::class.java.classLoader) as? Long,
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList(),
            parcel.createStringArrayList(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeStringList(phoneList)
        parcel.writeStringList(emailList)
        parcel.writeInt(iconColor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ContactParcelable> {
        override fun createFromParcel(parcel: Parcel): ContactParcelable {
            return ContactParcelable(parcel)
        }

        override fun newArray(size: Int): Array<ContactParcelable?> {
            return arrayOfNulls(size)
        }
    }
}