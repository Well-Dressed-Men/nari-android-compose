package com.welldressedmen.nari.domain

import com.welldressedmen.nari.data.remote.common.Resource
import com.welldressedmen.nari.data.remote.model.response.UserInfoResponse
import com.welldressedmen.nari.data.remote.repository.UserInfoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SurveyUseCase @Inject constructor(
    private val surveyRepository: UserInfoRepository,
    private val defaultDispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(
        userSex: String,
        userCold: String,
        userHot: String,
        userBody: String,
        userPreferences: String
    ): Flow<Resource<UserInfoResponse>> = flow {
        try {
            emit(Resource.loading())
            val userInfo = surveyRepository.updateUserInfo(userSex, userCold, userHot, userBody, userPreferences)
            emit(Resource.success(userInfo))
        } catch (e: Throwable) {
            emit(Resource.error(e))
        }
    }


}