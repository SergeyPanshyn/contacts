package com.example.sergey.contacts.presentation.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import butterknife.BindView
import com.example.sergey.contacts.R
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import android.content.Intent
import android.util.Log
import android.widget.Toast
import butterknife.ButterKnife
import com.example.sergey.contacts.extension.setLoggedIn
import com.example.sergey.contacts.presentation.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException

class LoginActivity : AppCompatActivity() {

    companion object {
        private const val SIGN_IN_REQUEST_CODE: Int = 123
    }

    @BindView(R.id.sign_in_button)
    lateinit var signInButton: SignInButton

    private var googleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        ButterKnife.bind(this)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        signInButton.setSize(SignInButton.SIZE_STANDARD)
        signInButton.setOnClickListener { signIn() }

    }

    private fun signIn() {
        val signInIntent = googleSignInClient?.signInIntent
        startActivityForResult(signInIntent, SIGN_IN_REQUEST_CODE)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_REQUEST_CODE) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Toast.makeText(this, "Successfully signed in with account ${account.email} ", Toast.LENGTH_SHORT).show()

            handleSuccessfulSignIn()
        } catch (e: ApiException) {
            Log.d("afdafadfad", e.message)
            Log.d("afdafadfad", e.localizedMessage)
            Log.d("afdafadfad", e.statusCode.toString())
            Log.d("afdafadfad", e.stackTrace.toString())
            e.printStackTrace()
            Toast.makeText(this, "Error sign in", Toast.LENGTH_SHORT).show()
        }

    }

    private fun handleSuccessfulSignIn() {
        this.setLoggedIn()

        startActivity(Intent(this, MainActivity::class.java))

        finish()
    }
}