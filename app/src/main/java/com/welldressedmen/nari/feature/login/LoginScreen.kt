package com.welldressedmen.nari.feature.login

import android.os.Message
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.welldressedmen.nari.R
import com.welldressedmen.nari.feature.main.Greeting


@Composable
fun LoginScreen(content: () -> Unit) {
    Surface (color = Color.White) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp),
            verticalArrangement = Arrangement.Center
        ) {
            LoginGreeting(text = "시작하시겠습니까?")
            SignInGoogleButton{ content() }
        }
    }
}

@Composable
fun LoginGreeting(text: String) {
    Text(text = text, style = MaterialTheme.typography.bodyMedium, color = Color.Gray, modifier = Modifier.padding(bottom = 12.dp))
}
@Composable
fun SignInGoogleButton(onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        shape = MaterialTheme.shapes.small,
        shadowElevation = 10.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(
                start = 14.dp,
                end = 12.dp,
                top = 11.dp,
                bottom = 11.dp
            )
        ) {
            Icon(painter = painterResource(id = R.drawable.logo_google), contentDescription = "Google sign button", tint = Color.Unspecified, modifier = Modifier.size(35.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = "Sign in with Google", style = MaterialTheme.typography.bodyMedium, color = Color.Gray, fontSize = 17.sp, fontWeight = FontWeight.Bold)
        }
    }
}