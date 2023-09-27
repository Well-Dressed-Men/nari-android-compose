package com.welldressedmen.nari.feature.main.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen() {
    Scaffold(
        topBar = { LocationTopBar() }
    ) {
        it
    }
}

@Composable
fun LocationTopBar() {
    Row(modifier = Modifier.padding(16.dp)) {
//        Icon(Icons.Filled.Arrow, contentDescription = "back")
    }
}