package com.welldressedmen.nari.feature.login

import SignInGoogleButton
import android.app.Activity
import android.util.Log
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
import com.google.android.gms.tasks.Task
import com.welldressedmen.nari.R

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel()) {

    Surface(color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "nari logo",
                modifier = Modifier.size(100.dp),
                Alignment.BottomCenter
            )
            Spacer(modifier = Modifier.size(100.dp))
            GoogleLogin(loginViewModel)
        }
    }
}

@Composable
fun GoogleLogin(vm: LoginViewModel) {
    val context = LocalContext.current
    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(intent)

                    val account = task.getResult(ApiException::class.java)
                    Log.d("google_login", "s $account")
                    Log.d("google_login", "s ${account.email}")
                    Log.d("google_login", "s ${account.id}")
                    Log.d("google_login", "s ${account.account}")
                    Log.d("google_login", "s ${account.displayName}")

                    val provider = account.displayName.toString()
                    val providerId = account.account?.type.toString()
                    val email = account.email.toString()
                    val name = account.displayName.toString()

                    vm.login(provider, providerId, email, name)

                }
            }
        }

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    SignInGoogleButton(onClick = { startForResult.launch(googleSignInClient.signInIntent) })

    vm.state.value.let { state ->
        when (state) {
            is UserUiStateReady -> {
                Text(text = state.user?.jwtToken.toString())
                Log.d("google_login", "s ${state.user?.jwtToken}")
            }
            is Loading -> {
                Log.d("google_login", "s Loading")
            }
            is UserUiStateError -> {
                Log.d("google_login", "s ${state.error}")
            }
        }
    }
}