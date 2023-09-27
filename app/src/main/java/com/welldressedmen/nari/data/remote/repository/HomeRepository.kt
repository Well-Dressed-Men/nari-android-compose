package com.welldressedmen.nari.data.remote.repository

import com.welldressedmen.nari.data.remote.api.ApiService
import com.welldressedmen.nari.data.remote.model.request.HomeRequestBody
import com.welldressedmen.nari.data.remote.model.response.HomeResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class HomeRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun home(homeRequestBody: HomeRequestBody) : HomeResponse = apiService.home(homeRequestBody)
}