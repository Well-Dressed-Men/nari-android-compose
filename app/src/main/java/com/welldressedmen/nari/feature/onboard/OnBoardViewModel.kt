package com.welldressedmen.nari.feature.onboard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welldressedmen.nari.data.remote.common.Resource
import com.welldressedmen.nari.data.remote.model.response.UserInfoResponse
import com.welldressedmen.nari.domain.SurveyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val surveyUseCase: SurveyUseCase,
) : ViewModel() {
    val state = mutableStateOf<OnBoardUiState>(Loading)
    var userSex: String = ""
    var userCold: String = ""
    var userHot: String = ""
    var userBody: String = ""
    var userPreferences: String = ""

    fun updateUserInfo() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            surveyUseCase.invoke(userSex, userCold, userHot, userBody, userPreferences).collect(::handleResponse)
        }
    }

    private suspend fun handleResponse(it: Resource<UserInfoResponse>) =
        withContext(Dispatchers.Main) {
            when (it.status) {
                Resource.Status.LOADING -> state.value = Loading

                Resource.Status.SUCCESS -> {
                    state.value = OnBoardUiStateReady(onBoard = it.data)
                }

                Resource.Status.ERROR -> state.value =
                    OnBoardUiStateError(error = it.error?.data?.message)
            }
        }
}

sealed class OnBoardUiState
data class OnBoardUiStateReady(val onBoard: UserInfoResponse?) : OnBoardUiState()
object Loading : OnBoardUiState()
class OnBoardUiStateError(val error: String? = null) : OnBoardUiState()
