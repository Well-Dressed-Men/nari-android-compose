package com.welldressedmen.nari.feature.main.home

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.opencsv.CSVReader
import com.opencsv.exceptions.CsvException
import com.welldressedmen.nari.preferences.LocationPreferences
import java.io.IOException
import java.io.InputStreamReader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen() {

    val context = LocalContext.current

    val list = mutableListOf<Location>()

    val assetManager = context.resources.assets
    val inputStream = assetManager.open("regionlist.csv")
    val csvReader = CSVReader(InputStreamReader(inputStream, "UTF-8"))
    try {
        csvReader.readAll().drop(1).forEach {
            val location = Location(
                id = it[0].toShort(),
                name = it[1] + " " + it[2],
                nx = it[3].toShort(),
                ny = it[4].toShort(),
                midLandCode = it[5],
                midTempCode = it[6]
            )
            list.add(location)
            Log.d("LocationPreferences", "s $location")
        }
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: CsvException) {
        e.printStackTrace()
    }

    Scaffold(
        topBar = { LocationTopBar() }
    ) {
        LazyColumn(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
            list.forEach {
                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp).clickable {
                        LocationPreferences.id = it.id.toInt()
                        LocationPreferences.name = it.name
                        LocationPreferences.nx = it.nx.toInt()
                        LocationPreferences.ny = it.ny.toInt()
                        LocationPreferences.midLandCode = it.midLandCode
                        LocationPreferences.midTempCode = it.midTempCode
                        LocationPreferences.stationName = it.name.split(" ").last()

                        (context as Activity).finish()

                    }) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(text = it.name, modifier = Modifier.fillMaxSize())
                        Spacer(modifier = Modifier.height(16.dp))
                        Divider(thickness = .5.dp)
                    }
                }
            }
        }
    }
}

@Composable
fun LocationTopBar() {
    Row(modifier = Modifier.padding(16.dp)) {
//        Icon(Icons.Filled.Arrow, contentDescription = "back")
    }
}