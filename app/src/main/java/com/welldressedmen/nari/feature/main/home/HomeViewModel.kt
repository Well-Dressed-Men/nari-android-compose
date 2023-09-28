package com.welldressedmen.nari.feature.main.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welldressedmen.nari.data.remote.common.Resource
import com.welldressedmen.nari.data.remote.model.response.InfoResponse
import com.welldressedmen.nari.domain.InfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val infoUseCase: InfoUseCase,
) : ViewModel() {
    val state = mutableStateOf<HomeUiState>(Loading)

    fun getTotalInfo(
        regionId: Short,
        nx: Short,
        ny: Short,
        midLandCode: String,
        midTempCode: String,
        stationName: String,
        ver: String,
    ) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            infoUseCase(regionId, nx, ny, midLandCode, midTempCode, stationName, ver).collect(::handleResponse)
        }
    }

    private suspend fun handleResponse(it: Resource<InfoResponse>) = withContext(Dispatchers.Main) {
        when (it.status) {
            Resource.Status.LOADING -> state.value = Loading
            Resource.Status.SUCCESS -> {
                state.value = HomeUiStateReady(home = it.data)
            }
            Resource.Status.ERROR -> state.value =
                HomeUiStateError(error = it.error?.data?.message)
        }
    }
}

sealed class HomeUiState
data class HomeUiStateReady(val home: InfoResponse?) : HomeUiState()
object Loading : HomeUiState()
class HomeUiStateError(val error: String? = null) : HomeUiState()
