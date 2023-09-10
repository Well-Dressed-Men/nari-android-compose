package com.welldressedmen.nari.feature.onboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.welldressedmen.nari.feature.main.Greeting

@Composable
fun OnBoardScreen(name: String) {
    Surface(
        modifier = Modifier.fillMaxSize(), // = match parent
        color = MaterialTheme.colorScheme.background
    ) {
        Greeting(name) // Hello Android 라는 텍스트를 넣는다.
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}