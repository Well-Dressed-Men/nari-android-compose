package com.welldressedmen.nari.feature.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.welldressedmen.nari.data.db.entity.User
import com.welldressedmen.nari.feature.onboard.OnBoardActivity

class LoginActivity : ComponentActivity() {
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 1313
    private var user = User("0", "default_name", "default_eamil")
    private val userViewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginScreen{ googleLogin() }
        }
    }

    fun googleLogin() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        googleSignIn()
    }

    fun googleSignIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                Log.d("google_login", "s $account")
                Log.d("google_login", "s ${account.email}")
                Log.d("google_login", "s ${account.id}")
                Log.d("google_login", "s ${account.account}")
                Log.d("google_login", "s ${account.displayName}")

                user.name = account.displayName.toString()
                user.email = account.email.toString()
                user.id = account.id.toString()
//                user.type = account.account?.type.toString()
                val provider = account.displayName.toString()
                val providerId = account.account?.type.toString()
                val email = account.email.toString()
                val name = account.displayName.toString()

                userViewModel.login(provider, providerId, email, name)

                val intent = Intent(this@LoginActivity, OnBoardActivity::class.java)
                intent.putExtra("id", user.id)
                startActivity(intent)

            } catch (e: ApiException) {
                Log.d("google_login", "f $e")
                Toast.makeText(this, "구글 회원가입에 실패하였습니다: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            /*no-op*/
        }
    }
}