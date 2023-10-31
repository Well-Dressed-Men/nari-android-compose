package com.welldressedmen.nari.data.remote.repository

import android.util.Log
import com.google.gson.JsonObject
import com.welldressedmen.nari.data.remote.api.ApiService
import com.welldressedmen.nari.data.remote.model.request.LoginRequestBody
import com.welldressedmen.nari.data.remote.model.response.LoginResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(provider: String, providerId : String, email : String, name : String) : LoginResponse {
        val loginJson = JsonObject()
        loginJson.addProperty("provider", provider)
        loginJson.addProperty("providerId", providerId)
        loginJson.addProperty("email", email)
        loginJson.addProperty("name", name)

        Log.d("LoginRequestBody", loginJson.toString())
        val loginRequestBody = LoginRequestBody(loginJson)

        return apiService.login(loginRequestBody)
    }
}