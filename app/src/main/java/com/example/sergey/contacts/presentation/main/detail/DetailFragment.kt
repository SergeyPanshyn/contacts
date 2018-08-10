package com.example.sergey.contacts.presentation.main.detail

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.sergey.contacts.R
import com.example.sergey.contacts.data.db.entity.Contact
import com.example.sergey.contacts.data.entity.ContactParcelable
import com.example.sergey.contacts.presentation.main.MainActivity
import com.example.sergey.contacts.presentation.main.detail.adapter.TextAdapter
import javax.inject.Inject

class DetailFragment : Fragment(), DetailPresenter.DetailView {

    @BindView(R.id.detail_fragment_name_tv)
    lateinit var nameTv: TextView

    @BindView(R.id.detail_fragment_phone_tv)
    lateinit var phoneTv: TextView

    @BindView(R.id.detail_fragment_phones_rv)
    lateinit var phonesRv: RecyclerView

    @BindView(R.id.detail_fragment_email_tv)
    lateinit var emailTv: TextView

    @BindView(R.id.detail_fragment_emails_rv)
    lateinit var emailRv: RecyclerView

    companion object {
        fun newInstance(contactParcelable: ContactParcelable): DetailFragment {
            val args = Bundle()
            args.putParcelable(DetailFragment::class.java.canonicalName, contactParcelable)

            val pacf = DetailFragment()
            pacf.arguments = args
            return pacf
        }
    }

    private val contact: ContactParcelable by lazy { arguments!!.getParcelable<ContactParcelable>(DetailFragment::class.java.canonicalName) }

    @Inject
    lateinit var detailPresenter: DetailPresenter<DetailPresenter.DetailView>

    private val detailListener by lazy { context as DetailListener }

    interface DetailListener {

        fun onContactDeleted()

        fun onEditContactClick(contactParcelable: ContactParcelable)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMenuVisibility(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragment = inflater.inflate(R.layout.detail_fragment, container, false)

        ButterKnife.bind(this, fragment)

        daggerInit()

        initUi()

        return fragment
    }

    private fun daggerInit() {
        (activity as MainActivity).detailComponent?.inject(this)
        detailPresenter.setView(this)
    }

    private fun initUi() {
        nameTv.text = getString(R.string.full_name, contact.firstName, contact.lastName)

        contact.phoneList?.let {
            if (!it.isEmpty()) {
                phoneTv.visibility = View.VISIBLE
                initPhonesRv(it)
            }
        }

        contact.emailList?.let {
            if (!it.isEmpty()) {
                emailTv.visibility = View.VISIBLE
                initEmailsRv(it)
            }
        }
    }

    private fun initPhonesRv(phones: List<String>) {
        phonesRv.layoutManager = LinearLayoutManager(context)
        phonesRv.adapter = TextAdapter(context!!, phones)
    }

    private fun initEmailsRv(emails: List<String>) {
        emailRv.layoutManager = LinearLayoutManager(context)
        emailRv.adapter = TextAdapter(context!!, emails)
    }

    @OnClick(R.id.detail_fragment_call_iv)
    fun onCallButtonClick() {
        if (contact.phoneList == null || contact.phoneList!!.isEmpty()) {
            Toast.makeText(context, "No phones was added", Toast.LENGTH_SHORT).show()
            return
        }

        if (contact.phoneList!!.size == 1) initCallIntent(contact.phoneList!![0]) else buildCallChooserDialog(contact.phoneList!!)

    }

    @OnClick(R.id.detail_fragment_email_iv)
    fun onEmailButtonClick() {
        if (contact.emailList == null || contact.emailList!!.isEmpty()) {
            Toast.makeText(context, "No emails was added", Toast.LENGTH_SHORT).show()
            return
        }

        if (contact.emailList!!.size == 1) initCallIntent(contact.emailList!![0]) else buildCallChooserDialog(contact.emailList!!)
    }

    @OnClick(R.id.detail_fragment_delete_button)
    fun onDeleteButtonClick() {
        detailPresenter.deleteContact(
                Contact(
                        contact.id,
                        contact.firstName,
                        contact.lastName,
                        contact.phoneList,
                        contact.emailList,
                        contact.iconColor
                )
        )
    }

    @OnClick(R.id.detail_fragment_edit_button)
    fun onFabClick() {
        detailListener.onEditContactClick(contact)
    }

    override fun onContactDeleted() {
        detailListener.onContactDeleted()
    }

    private fun buildCallChooserDialog(phones: List<String>) {
        AlertDialog.Builder(context!!)
                .setTitle("Choose phone")
                .setItems(phones.toTypedArray()) { _, which ->
                    initCallIntent(phones[which])
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
    }

    private fun buildEmailChooserDialog(emails: List<String>) {
        AlertDialog.Builder(context!!)
                .setTitle("Choose email")
                .setItems(emails.toTypedArray()) { _, which ->
                    initEmailIntent(emails[which])
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .create()
                .show()
    }

    private fun initCallIntent(phone: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phone")
        if (ActivityCompat.checkSelfPermission(context!!,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        startActivity(callIntent)
    }

    private fun initEmailIntent(email: String) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "vnd.android.cursor.dir/email"
        emailIntent.flags = (Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        emailIntent.putExtra(Intent.EXTRA_EMAIL,
                arrayOf(email))
        activity?.startActivity(Intent.createChooser(emailIntent, "Send email..."))
    }

}