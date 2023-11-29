package com.welldressedmen.nari.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("weatherInfo")
    val weatherResponse: WeatherResponse,
    @SerializedName("version")
    val version: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("fashionInfo")
    val fashionInfo: FashionInfo
)

data class WeatherResponse(
    @SerializedName("weatherUltraShort")
    val weatherUltraShort: List<WeatherUltraShort>,
    @SerializedName("weatherShort")
    val weatherShort: List<WeatherShort>,
    @SerializedName("weatherMid")
    val weatherMid: List<WeatherMid>,
    @SerializedName("weatherAP")
    val weatherAP: List<WeatherAP>
)

data class WeatherUltraShort(
    @SerializedName("fcstDate")
    val fcstDate: Int,
    @SerializedName("fcstTime")
    val fcstTime: Int,
    @SerializedName("temp")
    val temp: Int,
    @SerializedName("rainAmount")
    val rainAmount: Int,
    @SerializedName("windSpeed")
    val windSpeed: Int,
    @SerializedName("humid")
    val humid: Int,
    @SerializedName("sky")
    val sky: Int
) {
    fun getTemp(): String = if (temp != 0) temp.toString().dropLast(1) + "°" else "$temp°"
    fun getSky(): String {
        if (sky < 10) {
            return when (sky) {
                1 -> "비"
                2 -> "비/눈"
                3 -> "눈"
                4 -> "소나기"
                5 -> "빗방울"
                6 -> "빗방울/눈날림"
                7 -> "눈날림"
                else -> "알수없음"
            }
        }
        return when (sky / 10) {
            1 -> "맑음"
            3 -> "구름많음"
            4 -> "흐림"
            else -> "알수없음"
        }
    }
}

data class WeatherShort(
    @SerializedName("fcstDate")
    val fcstDate: Int,
    @SerializedName("fcstTime")
    val fcstTime: Int,
    @SerializedName("temp")
    val temp: Int,
    @SerializedName("rainAmount")
    val rainAmount: Int,
    @SerializedName("windSpeed")
    val windSpeed: Int,
    @SerializedName("humid")
    val humid: Int,
    @SerializedName("sky")
    val sky: Int,
    @SerializedName("rainPercentage")
    val rainPercentage: Int
)

data class WeatherMid(
    @SerializedName("fcstDate")
    val fcstDate: Int,
    @SerializedName("rainPercentageAm")
    val rainPercentageAm: Int,
    @SerializedName("rainPercentagePm")
    val rainPercentagePm: Int,
    @SerializedName("skyAm")
    val skyAm: Int,
    @SerializedName("skyPm")
    val skyPm: Int,
    @SerializedName("tempLowest")
    val tempLowest: Int,
    @SerializedName("tempHighest")
    val tempHighest: Int
)

data class WeatherAP(
    @SerializedName("fcstDate")
    val fcstDate: Int,
    @SerializedName("fcstTime")
    val fcstTime: Int,
    @SerializedName("pm10Value")
    val pm10Value: Int,
    @SerializedName("pm25Value")
    val pm25Value: Int
)

data class FashionInfo(
    @SerializedName("fashionStr")
    val fashionStr: List<String>
)