package com.welldressedmen.nari.data.remote.repository

import com.welldressedmen.nari.data.remote.api.ApiService
import com.welldressedmen.nari.data.remote.model.response.InfoResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class InfoRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getTotalInfo(
        regionId: Short,
        nx: Short,
        ny: Short,
        midLandCode: String,
        midTempCode: String,
        stationName: String,
        ver: String,
    ): InfoResponse = apiService.getTotalInfo(regionId, nx, ny, midLandCode, midTempCode, stationName, ver)
}