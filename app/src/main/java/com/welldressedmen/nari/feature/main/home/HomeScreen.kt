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
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.welldressedmen.nari.R
import com.welldressedmen.nari.data.remote.model.response.InfoResponse
import com.welldressedmen.nari.feature.common.LoadingBar
import com.welldressedmen.nari.feature.common.ShowToast
import com.welldressedmen.nari.data.db.preferences.LocationPreferences
import com.welldressedmen.nari.ui.theme.md_theme_light_surface
import java.text.SimpleDateFormat
import java.util.Calendar

@Composable
fun HomeScreen(vm: HomeViewModel = hiltViewModel()) {

    var location by remember { mutableStateOf(LocationPreferences.name) }

    val context = LocalContext.current
//    getLocation(context)

    OnLifecycleEvent { owner, event ->  
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                vm.getTotalInfo()
                location = LocationPreferences.name
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        md_theme_light_surface,
                        Color(0x448dccff)
                    )
                )
            )
    ) {
        // Top App Bar
        HomeScreenTopAppBar(location)

        vm.state.value.let { state ->
            when (state) {
                is Loading -> LoadingBar()
                is HomeUiStateReady -> state.home?.let { HomeScreenUI(it) }
                is HomeUiStateError -> {
                    ShowToast(text = state.error ?: "에러")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTopAppBar(location: String) {

    val context = LocalContext.current

    // TODO: 지역 데이터 받아오기
    // TODO: 내 지역 데이터 로컬에서 받아오기
    val test_location = arrayOf(
        "강희남 집",
        "김태환 집",
        "곽용우 집",
        "이지원 공주님 성",
    )

//    var location: String by remember { mutableStateOf(test_location[0]) }
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
                    ),
                    modifier = Modifier.clickable {
                        context.startActivity(Intent(context, LocationActivity::class.java))
                    }
                )
                Spacer(modifier = Modifier.width(4.dp))

//                Icon(
//                    imageVector = Icons.Filled.ArrowDropDown,
//                    contentDescription = "spinner drop down icon"
//                )
            }

//            DropdownMenu(
//                expanded = expanded,
//                onDismissRequest = { expanded = false },
//            ) {
//                test_location.forEach { city ->
//                    DropdownMenuItem(onClick = {
//                        expanded = false
//                        location = city
//                    }) {
//                        val isSelected = city == location
//                        val style = if (isSelected) {
//                            MaterialTheme.typography.bodyLarge.copy(
//                                fontWeight = FontWeight.Bold,
//                                color = MaterialTheme.colorScheme.primary
//                            )
//                        } else {
//                            MaterialTheme.typography.bodyLarge.copy(
//                                fontWeight = FontWeight.Normal,
//                                color = MaterialTheme.colorScheme.onSurface
//                            )
//                        }
//                        Text(text = city, style = style)
//                    }
//
//                    Divider(modifier = Modifier.padding(horizontal = 8.dp), thickness = 0.5.dp)
//                }
//
//                DropdownMenuItem(onClick = {
//                    expanded = false
//                    context.startActivity(Intent(context, LocationActivity::class.java))
//                }) {
//                    Text(
//                        text = "새 지역 추가 +",
//                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
//                    )
//                }
//            }

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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeScreenUI(data: InfoResponse) {
    var index: Int by remember {
        mutableIntStateOf(0)
    }

    val weather = data.weatherResponse
    val url = data.fashionInfo.fashionStr.map {
        "https://recommend-images.s3.ap-northeast-2.amazonaws.com/" +
                it.split("-")[1].split(".jpg")[0] +
                "/" + it
    }
    Log.d("HomeScreenUI", "HomeScreenUI: ${url[0]}")
    val current = weather.weatherUltraShort[1]

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
                    // Temp
                    Text(text = current.getTemp(), style = MaterialTheme.typography.displayLarge)
                    // Sky
                    Text(
                        text = current.getSky(),
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    // High, Low
                    Text(
                        text = "${
                            weather.weatherMid[0].tempHighest.toString().dropLast(1)
                        }° / ${weather.weatherMid[0].tempLowest.toString().dropLast(1)}°",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Image(
                    painter = painterResource(id = getImageId(current.sky)),
                    contentDescription = null,
                    modifier = Modifier
                        .height(120.dp)
                        .offset(x = (-16).dp)
                        .scale(2.0f)
                )
            }
        }

        // 사진
        item {
            GlideImage(
                model = url[index % url.size],
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .aspectRatio(1f)
                    .clickable { index += 1 },
                contentScale = ContentScale.FillWidth
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
                weather.weatherShort.forEachIndexed { index, it ->
                    val temp = if (it.fcstTime == 0) 0
                    else it.fcstTime.toString().dropLast(2).toInt()

                    item {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = if (temp < 12) "오전 ${temp}시" else "오후 ${if (temp == 12) temp else temp - 12}시",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.outline
                                )
                            )
                            Image(
                                painter = painterResource(id = getImageId(it.sky)),
                                contentDescription = null,
                                modifier = Modifier
                                    .scale(2.0f)
                                    .height(24.dp)
                                    .width(24.dp)
                            )
                            Text(
                                text = "${if (it.temp != 0) it.temp.toString().dropLast(1) else it.temp}°",
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
                                    text = "${it.rainPercentage}%",
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
                                text = "${current.rainAmount}%",
                                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W500)
                            )
                        }
                        Image(
                            painter = painterResource(id = getImageId(current.sky)),
                            contentDescription = null,
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp)
                                .scale(1.5f)
                        )
                        Image(
                            painter = painterResource(id = getImageId(current.sky)),
                            contentDescription = null,
                            modifier = Modifier
                                .height(32.dp)
                                .width(32.dp)
                                .scale(1.5f)
                        )
                        Text(
                            text = weather.weatherMid[0].tempHighest.toString().dropLast(1),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
                        )
                        Text(
                            text = weather.weatherMid[0].tempLowest.toString().dropLast(1),
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
                        )
                    }
                }




                weather.weatherMid.forEachIndexed { index, it ->
                    val sdp = SimpleDateFormat("E요일")
                    val first = Calendar.getInstance()
                    first.add(Calendar.DAY_OF_MONTH, index + 1)

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = sdp.format(first.time),
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
                                    text = "${it.rainPercentageAm}%",
                                    style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.W500)
                                )
                            }
                            Image(
                                painter = painterResource(id = getImageId(it.skyAm)),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(32.dp)
                                    .width(32.dp)
                                    .scale(1.5f)
                            )
                            Image(
                                painter = painterResource(id = getImageId(it.skyPm)),
                                contentDescription = null,
                                modifier = Modifier
                                    .height(32.dp)
                                    .width(32.dp)
                                    .scale(1.5f)
                            )
                            Text(
                                text = if (it.tempHighest != 0) it.tempHighest.toString().dropLast(1) + "°" else it.tempHighest.toString() + "°",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
                            )
                            Text(
                                text = if (it.tempLowest != 0) it.tempLowest.toString().dropLast(1) + "°" else it.tempLowest.toString() + "°",
                                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
                            )
                        }
                    }
                }
            }
        }

//        item {
//            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .aspectRatio(1.2f)
//                        .background(
//                            color = MaterialTheme.colorScheme.surface,
//                            shape = RoundedCornerShape(24.dp)
//                        )
//                        .border(
//                            width = 1.dp,
//                            color = MaterialTheme.colorScheme.surfaceVariant,
//                            shape = RoundedCornerShape(24.dp)
//                        ),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "미세먼지",
//                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600)
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "좋음 23㎍/m³",
//                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline)
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    LinearProgressIndicator(
//                        progress = .4f,
//                        modifier = Modifier
//                            .padding(horizontal = 32.dp)
//                            .height(10.dp)
//                            .clip(RoundedCornerShape(16.dp)),
//                    )
//                }
//
//                Spacer(modifier = Modifier.width(16.dp))
//
//                Column(
//                    modifier = Modifier
//                        .weight(1f)
//                        .aspectRatio(1.2f)
//                        .background(
//                            color = MaterialTheme.colorScheme.surface,
//                            shape = RoundedCornerShape(24.dp)
//                        )
//                        .border(
//                            width = 1.dp,
//                            color = MaterialTheme.colorScheme.surfaceVariant,
//                            shape = RoundedCornerShape(24.dp)
//                        ),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "초미세먼지",
//                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600)
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(
//                        text = "좋음 9㎍/m³",
//                        style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.outline)
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    LinearProgressIndicator(
//                        progress = .23f,
//                        modifier = Modifier
//                            .padding(horizontal = 32.dp)
//                            .height(10.dp)
//                            .clip(RoundedCornerShape(16.dp)),
//                    )
//                }
//            }
//        }
    }
}

@Composable
fun OnLifecycleEvent(onEvent: (owner: LifecycleOwner, event: Lifecycle.Event) -> Unit) {
    val eventHandler = rememberUpdatedState(onEvent)
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)

    DisposableEffect(lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, event ->
            eventHandler.value(owner, event)
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}

fun getImageId(sky: Int): Int {
    return when (sky) {
        1 -> R.drawable.weather1
        10 -> R.drawable.weather10
        3 -> R.drawable.weather3
        30 -> R.drawable.weather30
        40 -> R.drawable.weather40
        else -> R.drawable.weather1
    }
}
