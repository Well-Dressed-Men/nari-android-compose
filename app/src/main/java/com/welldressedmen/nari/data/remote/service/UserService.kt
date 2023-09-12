package com.welldressedmen.nari.data.remote.service

import com.welldressedmen.nari.data.remote.request.LoginRequestBody
import com.welldressedmen.nari.data.remote.response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("oauth/jwt/google")
    fun login(@Body requestBody: LoginRequestBody) : Call<LoginResponse>
}