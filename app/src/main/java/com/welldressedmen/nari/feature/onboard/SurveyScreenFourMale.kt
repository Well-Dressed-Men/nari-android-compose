package com.welldressedmen.nari.feature.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.welldressedmen.nari.R

@Composable
fun SurveyScreenFourMale(onClick: () -> Unit) {
    val clicked1 = remember { mutableStateOf(false) }
    val clicked2 = remember { mutableStateOf(false) }
    val clicked3 = remember { mutableStateOf(false) }
    val clicked4 = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
            //    .background(Color.Cyan)
            , contentAlignment = Alignment.Center
        ) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    //    .background(Color.Magenta)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        //   .background(Color.LightGray),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "더위를 많이 타시나요?",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Image(
                            painter = painterResource(id = R.drawable.chick),
                            contentDescription = "nari logo",
                            modifier = Modifier
                                .size(200.dp)
                        )
                    }
                }
                Box() {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        //items(1){
                        Button(
                            onClick = { clicked1.value = !clicked1.value
                                clicked2.value = false
                                clicked3.value = false
                                clicked4.value = false},
                            colors = ButtonDefaults.buttonColors(
                                if (clicked1.value) Color(0xFF42A0FB) else Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(text = "마른", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { clicked2.value = !clicked2.value;
                                clicked1.value = false
                                clicked3.value = false
                                clicked4.value = false},
                            colors = ButtonDefaults.buttonColors(
                                if (clicked2.value) Color(0xFF42A0FB) else Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(text = "보통", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { clicked3.value = !clicked3.value;
                                clicked2.value = false
                                clicked1.value = false
                                clicked4.value = false},
                            colors = ButtonDefaults.buttonColors(
                                if (clicked3.value) Color(0xFF42A0FB) else Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(text = "통통", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = { clicked4.value = !clicked4.value;
                                clicked2.value = false
                                clicked3.value = false
                                clicked1.value = false},
                            colors = ButtonDefaults.buttonColors(
                                if (clicked4.value) Color(0xFF42A0FB) else Color.White
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            shape = RoundedCornerShape(16.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            Text(text = "근육있는", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }

        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentPadding = PaddingValues(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF18CD8C),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "다음", fontSize = 20.sp, style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }
    }
}