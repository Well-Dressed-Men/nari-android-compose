package com.welldressedmen.nari.feature.onboard

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class OnBoardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("intent_success", "intent success")
        
        if (intent.hasExtra("id")) {
            val userName = intent.getStringExtra("id")
            if (userName == null)
                setContent { OnBoardScreen(name = "Android") }
            else
                setContent{ OnBoardScreen(name = userName)}
        }
    }
}