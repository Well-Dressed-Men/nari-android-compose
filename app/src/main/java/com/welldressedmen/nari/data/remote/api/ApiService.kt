package com.welldressedmen.nari.data.remote.api

import com.welldressedmen.nari.data.remote.model.request.LoginRequestBody
import com.welldressedmen.nari.data.remote.model.response.InfoResponse
import com.welldressedmen.nari.data.remote.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("oauth/jwt/google")
    suspend fun login(@Body requestBody: LoginRequestBody): LoginResponse

    @GET("api/v1/weather-clothing-infos")
    suspend fun getTotalInfo(
        @Query("regionId") regionId: Short,
        @Query("nx") nx: Short,
        @Query("ny") ny: Short,
        @Query("midTempCode") midTempCode: String,
        @Query("midLandCode") midLandCode: String,
        @Query("stationName") stationName: String,
        @Query("ver") ver: String
    ): InfoResponse
}