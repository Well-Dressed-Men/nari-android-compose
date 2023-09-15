package com.welldressedmen.nari.feature.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welldressedmen.nari.data.remote.common.Resource
import com.welldressedmen.nari.data.remote.model.response.LoginResponse
import com.welldressedmen.nari.domain.UserUseCase
import com.welldressedmen.nari.preferences.UserPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase
): ViewModel() {
    val state = mutableStateOf<UserUiState>(Loading)

    fun login(provider: String, providerId : String, email : String, name : String) = viewModelScope.launch {
        userUseCase(provider, providerId, email, name).collect(::handleResponse)
    }

    private suspend fun handleResponse(it: Resource<LoginResponse>) = withContext(
        Dispatchers.Main) {
        when (it.status) {
            Resource.Status.LOADING -> state.value = Loading
            Resource.Status.SUCCESS -> {
                state.value = UserUiStateReady(user = it.data)
                if (it.data?.jwtToken == null)
                    Log.d("jwtToken", it.data.toString())
                else
                    UserPreferences.userId = it.data.jwtToken
            }
            Resource.Status.ERROR -> state.value =
                UserUiStateError(error = it.error?.data?.message)
        }
    }
}

sealed class UserUiState
data class UserUiStateReady(val user: LoginResponse?) : UserUiState()
object Loading : UserUiState()
class UserUiStateError(val error: String? = null) : UserUiState()