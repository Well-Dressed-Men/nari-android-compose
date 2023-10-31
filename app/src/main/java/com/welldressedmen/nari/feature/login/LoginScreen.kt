package com.welldressedmen.nari.feature.login

import SignInGoogleButton
import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.welldressedmen.nari.R
import com.welldressedmen.nari.feature.onboard.OnBoardActivity
import com.welldressedmen.nari.preferences.UserPreferences

@Composable
fun LoginScreen(viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var launcher = rememberGoogleSignInLauncher(
        onAuthComplete = { account ->
            val provider = "google"
            val providerId = account.id!!
            val email = account.email.toString()
            val name = account.displayName.toString()

            Log.d("LoginScreen", "s $provider")
            Log.d("LoginScreen", "s $providerId")
            Log.d("LoginScreen", "s $email")
            Log.d("LoginScreen", "s $name")
            viewModel.login(provider, providerId, email, name)
        }
    )
    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.chick),
                contentDescription = "nari logo",
                modifier = Modifier
                    .size(100.dp),
                Alignment.BottomCenter
            )
            Spacer(modifier = Modifier.size(100.dp))
            SignInGoogleButton {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build()
                val googleSignInClient = GoogleSignIn.getClient(context, gso)
                launcher.launch(googleSignInClient.signInIntent)
            }

            viewModel.state.value.let { state ->
                when (state) {
                    is UserUiStateReady -> {
                        UserPreferences.userId = state.user!!.jwtToken
                        Text(state.user!!.jwtToken)
                        Log.d("LoginScreen", "s ${state.user?.jwtToken}")
                        val intent = Intent(context, OnBoardActivity::class.java)
                        context.startActivity(intent)
                        (context as Activity).finish()
                    }

                    is Loading -> {
                        Log.d("LoginScreen", "s Loading")
                    }

                    is UserUiStateError -> {
                        Log.d("LoginScreen", "s ${state.error}")
                    }
                }
            }
        }
    }
}

@Composable
fun rememberGoogleSignInLauncher(
    onAuthComplete: (GoogleSignInAccount) -> Unit,
): ManagedActivityResultLauncher<Intent, ActivityResult> {
    return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            onAuthComplete(account)
        } catch (e: ApiException) {
            Log.d("LoginScreen", "Google sign in failed", e)
        }
    }
}