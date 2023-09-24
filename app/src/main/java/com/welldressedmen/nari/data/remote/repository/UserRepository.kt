package com.welldressedmen.nari.data.remote.repository

import com.welldressedmen.nari.data.remote.api.ApiService
import com.welldressedmen.nari.data.remote.model.request.LoginRequestBody
import com.welldressedmen.nari.data.remote.model.response.LoginResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import org.json.JSONObject
import javax.inject.Inject

@ActivityRetainedScoped
class UserRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(provider: String, providerId : String, email : String, name : String) : LoginResponse {
        val loginJson = JSONObject()
        loginJson.put("provider", provider)
        loginJson.put("providerId", providerId)
        loginJson.put("email", email)
        loginJson.put("name", name)
        val loginRequestBody = LoginRequestBody(loginJson)

        return apiService.login(loginRequestBody)
    }
}