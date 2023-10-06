package com.welldressedmen.nari.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.welldressedmen.nari.feature.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // for mainActivity debugging
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        setContent {
            LoginScreen()
        }
    }
}