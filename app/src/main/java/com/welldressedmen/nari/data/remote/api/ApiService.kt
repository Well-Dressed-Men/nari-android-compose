package com.welldressedmen.nari.data.remote.api

import com.welldressedmen.nari.data.remote.model.request.HomeRequestBody
import com.welldressedmen.nari.data.remote.model.request.LoginRequestBody
import com.welldressedmen.nari.data.remote.model.response.HomeResponse
import com.welldressedmen.nari.data.remote.model.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("oauth/jwt/google")
    suspend fun login(@Body requestBody: LoginRequestBody) : LoginResponse

    @POST("HOME") // TODO: Change address
    suspend fun home(@Body requestBody: HomeRequestBody) : HomeResponse // TODO: Change function name
}