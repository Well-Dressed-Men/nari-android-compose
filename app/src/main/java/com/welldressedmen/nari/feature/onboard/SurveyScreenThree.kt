package com.welldressedmen.nari.feature.onboard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SurveyScreenThree(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "three")
    }
}