package com.welldressedmen.nari.domain

import android.util.Log
import com.welldressedmen.nari.data.remote.common.Resource
import com.welldressedmen.nari.data.remote.model.response.InfoResponse
import com.welldressedmen.nari.data.remote.repository.InfoRepository
import com.welldressedmen.nari.preferences.LocationPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InfoUseCase @Inject constructor(
    private val repository: InfoRepository,
    private val defaultDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(): Flow<Resource<InfoResponse>> =
        flow<Resource<InfoResponse>> {
            try {
                emit(Resource.loading())
                val info = repository.getTotalInfo(
                    LocationPreferences.id.toShort(),
                    LocationPreferences.nx.toShort(),
                    LocationPreferences.ny.toShort(),
                    LocationPreferences.midTempCode,
                    LocationPreferences.midLandCode,
                    LocationPreferences.stationName,
                    "1.0"
                )
                Log.d("InfoUseCase", "invoke: $info")
                emit(Resource.success(info))
            } catch (e: Throwable) {
                Log.d("InfoUseCase", "invoke errore: $e")
                emit(Resource.error(e))
            }
        }.flowOn(defaultDispatcher)

    suspend operator fun invoke(
        regionId: Short,
        nx: Short,
        ny: Short,
        midTempCode: String,
        midLandCode: String,
        stationName: String,
        ver: String,
    ): Flow<Resource<InfoResponse>> = flow {
        try {
            emit(Resource.loading())
            val info = repository.getTotalInfo(
                regionId,
                nx,
                ny,
                midTempCode,
                midLandCode,
                stationName,
                ver
            )
            emit(Resource.success(info))
        } catch (e: Throwable) {
            emit(Resource.error(e))
        }
    }
}