package com.welldressedmen.nari.data.remote.repository

import android.util.Log
import com.welldressedmen.nari.data.remote.api.ApiService
import com.welldressedmen.nari.data.remote.model.response.UserInfoResponse
import com.welldressedmen.nari.preferences.UserPreferences
import dagger.hilt.android.scopes.ActivityRetainedScoped
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import javax.inject.Inject

@ActivityRetainedScoped
class UserInfoRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun updateUserInfo(
        userSex: String,
        userCold: String,
        userHot: String,
        userBody: String,
        userPreferences: String,
    ) : UserInfoResponse {
        val userInfoJson = JSONObject()
        userInfoJson.put("memberSex", userSex)
        userInfoJson.put("memberCold", userCold)
        userInfoJson.put("memberHot", userHot)
        userInfoJson.put("memberBody", userBody)
        userInfoJson.put("memberPreferences", userPreferences)
        val userInfoRequestBody = userInfoJson.toString().toRequestBody("application/json".toMediaTypeOrNull())

        Log.d("UserInfoRequestBody", userInfoJson.toString())
        Log.d("UserInfoRequestBody", "Bearer " + UserPreferences.userId)

        return apiService.updateUserInfo(header = "Bearer " + UserPreferences.userId, requestBody = userInfoRequestBody)
    }
}
