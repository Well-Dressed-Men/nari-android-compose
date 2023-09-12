package com.welldressedmen.nari.data.remote.repository

import com.welldressedmen.nari.data.remote.RetrofitClient
import com.welldressedmen.nari.data.remote.request.LoginRequestBody
import com.welldressedmen.nari.data.remote.response.LoginResponse
import org.json.JSONObject
import retrofit2.Response

class UserRepository {
    private val userService = RetrofitClient.userService
    suspend fun login(provider: String, providerId : String, email : String, name : String) : Response<LoginResponse> {
        val loginJson = JSONObject()
        loginJson.put("provider", provider)
        loginJson.put("providerId", providerId)
        loginJson.put("email", email)
        loginJson.put("name", name)
        val loginRequestBody = LoginRequestBody(loginJson)

        return userService.login(loginRequestBody).execute()
    }
}