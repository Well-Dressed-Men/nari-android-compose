package com.welldressedmen.nari.feature.main.home

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.welldressedmen.nari.R
import com.welldressedmen.nari.data.remote.model.response.InfoResponse
import com.welldressedmen.nari.feature.common.LoadingBar
import com.welldressedmen.nari.feature.common.ShowToast

@Composable
fun HomeScreen(vm: HomeViewModel = hiltViewModel()) {

//    vm.getTotalInfo(1, 60, 126, "11B00000", "11B10101", "중구", "1.0")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        // Top App Bar
        HomeScreenTopAppBar()

        vm.state.value.let { state ->
            when (state) {
                is Loading -> LoadingBar()
                is HomeUiStateReady -> state.home?.let { HomeScreenUI(it) }
                is HomeUiStateError -> ShowToast(text = state.error ?: "에러")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopAppBar() {

    val context = LocalContext.current

    // TODO: 지역 데이터 받아오기
    // TODO: 내 지역 데이터 로컬에서 받아오기
    val test_location = arrayOf(
        "강희남 집",
        "김태환 집",
        "곽용우 집",
        "이지원 공주님 성",
    )

    var location: String by remember { mutableStateOf(test_location[0]) }
    var expanded by remember { mutableStateOf(false) }

    // Top App Bar
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .clickable { expanded = !expanded }
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = location,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.width(4.dp))

                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = "spinner drop down icon"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                test_location.forEach { city ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        location = city
                    }) {
                        val isSelected = city == location
                        val style = if (isSelected) {
                            MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        } else {
                            MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(text = city, style = style)
                    }

                    Divider(modifier = Modifier.padding(horizontal = 8.dp), thickness = 0.5.dp)
                }

                DropdownMenuItem(onClick = {
                    expanded = false
                    context.startActivity(Intent(context, LocationActivity::class.java))
                }) {
                    Text(
                        text = "새 지역 추가 +",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                    )
                }
            }

//            IconButton(
//                onClick = {
//                    context.startActivity(Intent(context, LocationActivity::class.java))
//                },
//                modifier = Modifier
//                    .indication(
//                        remember { MutableInteractionSource() },
//                        rememberRipple(radius = 64.dp, bounded = true)
//                    )
//            ) {
//                Icon(
//                    imageVector = ImageVector.vectorResource(id = R.drawable.add_24px),
//                    contentDescription = null
//                )
//            }
        }
    }
}

@Composable
fun HomeScreenUI(data: InfoResponse) {

    Log.d("getTotalInfo", data.toString())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // 날씨 카드
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .wrapContentHeight()
                ) {
                    Text(text = "24°", style = MaterialTheme.typography.displayLarge)
                    Text(
                        text = "구름 많음",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "26° / 22° 체감온도 22°",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.cloud_sunny),
                    contentDescription = null,
                    modifier = Modifier
                        .height(120.dp)
                        .offset(x = (-24).dp)
                        .scale(2.0f)
                )
            }
        }

        // 사진
        item {
            Image(
                painter = painterResource(id = R.drawable.sample_home_image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
        }

        // 시간별 날씨
        item {
            LazyRow(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .border(
                        width = 0.5.dp,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(24.dp)
                    )
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(24) {
                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "오전 ${it}시",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            )
                            Image(
                                painter = painterResource(id = R.drawable.sun),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(2.0f)
                                    .height(24.dp)
                                    .width(24.dp)
                            )
                            Text(
                                text = " 21°",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold)
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.rain_chance_30_24px),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(12.dp)
                                        .width(12.dp)
                                )
                                Text(
                                    text = "30%",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = MaterialTheme.colorScheme.outline,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }

        // 주간 날씨
        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(7) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "오늘",
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600)
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = R.drawable.rain_chance_30_24px),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(12.dp)
                                        .width(12.dp)
                                )
                                Text(
                                    text = "30%",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W500)
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.sun),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(32.dp)
                                    .width(32.dp)
                                    .scale(1.5f)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.cloud),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(32.dp)
                                    .width(32.dp)
                                    .scale(1.5f)
                            )
                            Text(
                                text = "21°",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
                            )
                            Text(
                                text = "19°",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
                            )
                        }
                    }
                }
            }
        }

        item {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1.2f)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(24.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "미세먼지",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "좋음 23㎍/m³",
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = .4f,
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .height(10.dp)
                            .clip(RoundedCornerShape(16.dp)),
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1.2f)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(24.dp)
                        ),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "초미세먼지",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "좋음 9㎍/m³",
                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = .23f,
                        modifier = Modifier
                            .padding(horizontal = 32.dp)
                            .height(10.dp)
                            .clip(RoundedCornerShape(16.dp)),
                    )
                }
            }
        }
    }
}
