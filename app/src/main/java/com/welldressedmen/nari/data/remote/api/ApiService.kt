package com.welldressedmen.nari.data.remote.api

import com.welldressedmen.nari.data.remote.model.response.LoginResponse
import com.welldressedmen.nari.data.remote.model.request.LoginRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("oauth/jwt/google")
    suspend fun login(@Body requestBody: LoginRequestBody) : LoginResponse
}