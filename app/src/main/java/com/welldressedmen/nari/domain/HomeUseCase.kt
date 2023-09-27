package com.welldressedmen.nari.domain

import com.welldressedmen.nari.data.remote.common.Resource
import com.welldressedmen.nari.data.remote.model.request.HomeRequestBody
import com.welldressedmen.nari.data.remote.model.response.HomeResponse
import com.welldressedmen.nari.data.remote.repository.HomeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val defaultDispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(
        homeRequestBody: HomeRequestBody
    ): Flow<Resource<HomeResponse>> = flow {
        try {
            emit(Resource.loading())
            val homeResponse = repository.home(homeRequestBody)
            emit(Resource.success(homeResponse))
        } catch (e: Throwable) {
            emit(Resource.error(e))
        }
    }.flowOn(defaultDispatcher)
}