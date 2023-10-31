package com.welldressedmen.nari.data.remote.api

import com.welldressedmen.nari.data.remote.model.request.LoginRequestBody
import com.welldressedmen.nari.data.remote.model.response.InfoResponse
import com.welldressedmen.nari.data.remote.model.response.LoginResponse
import com.welldressedmen.nari.data.remote.model.response.UserInfoResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("oauth/jwt/google")
    suspend fun login(@Body requestBody: LoginRequestBody): LoginResponse

    @Headers("Content-Type: application/json")
    @GET("api/v1/weather-clothing-infos")
    suspend fun getTotalInfo(
        @Header("Authorization") header: String,
        @Query("regionId") regionId: Short,
        @Query("nx") nx: Short,
        @Query("ny") ny: Short,
        @Query("midTempCode") midTempCode: String,
        @Query("midLandCode") midLandCode: String,
        @Query("stationName") stationName: String,
        @Query("ver") ver: String,
    ): InfoResponse

    @POST("/api/v1/users")
    suspend fun updateUserInfo(
        @Header("Authorization") header: String,
        @Body requestBody: RequestBody,
    ): UserInfoResponse
}