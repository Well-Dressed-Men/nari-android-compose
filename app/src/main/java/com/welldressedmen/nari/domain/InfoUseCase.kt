package com.welldressedmen.nari.domain

import com.welldressedmen.nari.data.remote.common.Resource
import com.welldressedmen.nari.data.remote.model.response.InfoResponse
import com.welldressedmen.nari.data.remote.repository.InfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class InfoUseCase @Inject constructor(
    private val repository: InfoRepository,
    private val defaultDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(
        regionId: Short,
        nx: Short,
        ny: Short,
        midLandCode: String,
        midTempCode: String,
        stationName: String,
        ver: String,
    ): Flow<Resource<InfoResponse>> = flow {
        try {
            emit(Resource.loading())
            val info = repository.getTotalInfo(regionId, nx, ny, midLandCode, midTempCode, stationName, ver)
            emit(Resource.success(info))
        } catch (e: Throwable) {
            emit(Resource.error(e))
        }
    }
}