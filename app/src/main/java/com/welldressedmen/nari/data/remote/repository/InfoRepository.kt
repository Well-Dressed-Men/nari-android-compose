package com.welldressedmen.nari.data.remote.repository

import com.welldressedmen.nari.data.remote.api.ApiService
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
        midTempCode: String,
        midLandCode: String,
        stationName: String,
        ver: String,
    ) = apiService.getTotalInfo(regionId, nx, ny, midTempCode, midLandCode, stationName, ver)
}