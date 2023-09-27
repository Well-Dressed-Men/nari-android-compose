package com.welldressedmen.nari.feature.main.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.welldressedmen.nari.ui.theme.NariTheme

class LocationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NariTheme {
                LocationScreen()
            }
        }
    }
}

