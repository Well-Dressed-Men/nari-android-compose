package com.welldressedmen.nari.data.remote.api

import com.welldressedmen.nari.data.remote.model.request.LoginRequestBody
import com.welldressedmen.nari.data.remote.model.response.InfoResponse
import com.welldressedmen.nari.data.remote.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("oauth/jwt/google")
    suspend fun login(@Body requestBody: LoginRequestBody): LoginResponse

    @POST("api/v1/weather-clothing-infos") // TODO: Change address
    suspend fun getTotalInfo(
        @Path("regionId") regionId: Short,
        @Path("nx") nx: Short,
        @Path("ny") ny: Short,
        @Path("midLandCode") midLandCode: String,
        @Path("midTempCode") midTempCode: String,
        @Path("stationName") stationName: String,
        @Path("ver") ver: String
    ): InfoResponse
}