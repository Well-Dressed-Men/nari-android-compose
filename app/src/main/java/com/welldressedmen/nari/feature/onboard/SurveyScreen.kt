package com.welldressedmen.nari.feature.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.welldressedmen.nari.R

@Composable
fun SurveyScreen(onClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth(), contentAlignment = Alignment.Center) {
            Column(verticalArrangement = Arrangement.Center
            ) {
                Text(text = "안녕! 시작하기 전에 간단한 질문 몇 가지만 할게")
                Image(
                    painter = painterResource(id = R.drawable.chick),
                    contentDescription = "nari logo",
                    modifier = Modifier.size(100.dp),
                    Alignment.BottomCenter
                )
            }
        }

        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF18CD8C),
                contentColor = Color.White
            )
        ) {
            Text(text = "시작하기")
        }
    }
}