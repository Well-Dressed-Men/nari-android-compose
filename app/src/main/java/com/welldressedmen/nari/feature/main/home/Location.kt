package com.welldressedmen.nari.feature.main.home

data class Location(
    val id: Short,
    val name: String,
    val nx: Short,
    val ny: Short,
    val midLandCode: String,
    val midTempCode: String,
)