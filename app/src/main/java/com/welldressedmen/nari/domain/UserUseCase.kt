package com.welldressedmen.nari.domain

import com.welldressedmen.nari.data.remote.common.Resource
import com.welldressedmen.nari.data.remote.model.response.LoginResponse
import com.welldressedmen.nari.data.remote.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: UserRepository,
    private val defaultDispatcher: CoroutineDispatcher,
) {

    private var user: LoginResponse = LoginResponse("")

    suspend operator fun invoke(
        provider: String,
        providerId: String,
        email: String,
        name: String,
    ): Flow<Resource<LoginResponse>> = flow {
        try {
            emit(Resource.loading())
            user = repository.login(provider, providerId, email, name)
            emit(Resource.success(user))
        } catch (e: Throwable) {
            emit(Resource.error(e))
        }
    }.flowOn(defaultDispatcher)
}