package com.welldressedmen.nari.feature.onboard

import android.util.Log
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
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.welldressedmen.nari.R

@Composable
fun SurveyScreenFive(vm: OnBoardViewModel, onClick: () -> Unit) {

    val context = LocalContext.current

    val answer = if (vm.userSex == "남성")
        arrayOf("캐주얼", "스트릿", "미니멀", "클래식/오피스룩")
    else
        arrayOf("캐주얼", "큐티/러블리", "클래식/오피스룩", "모던시크", "미니멀", "키치", "스트릿")

    var select = remember { mutableStateMapOf<String, Boolean>() }

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
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
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
                            text = "좋아하는 스타일이 뭔가요?",
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
                        answer.forEach {
                            Button(
                                onClick = {
                                    if (select[it] == null) {
                                        select[it] = true
                                    } else {
                                        select[it] = !select[it]!!
                                    }
                                    vm.userPreferences =
                                        select.filter { it.value }.keys.joinToString(" ")
                                    Log.d(
                                        "SurveyScreenFive",
                                        "vm.userPreferences: ${vm.userPreferences}"
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(
                                    if (select[it] != null && select[it]!!) Color(0xFF42A0FB) else Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                shape = RoundedCornerShape(16.dp),
                                contentPadding = PaddingValues(16.dp)
                            ) {
                                Text(text = it, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            }
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
                text = "설문 종료",
                fontSize = 20.sp, style = TextStyle(
                    platformStyle = PlatformTextStyle(
                        includeFontPadding = false
                    )
                )
            )
        }
    }
}