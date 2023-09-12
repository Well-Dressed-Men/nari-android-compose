package com.welldressedmen.nari.data.remote.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.welldressedmen.nari.lib.getLog
import com.welldressedmen.nari.data.remote.repository.UserRepository
import com.welldressedmen.nari.data.remote.response.LoginResponse
import com.welldressedmen.nari.preferences.UserPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository()

    private val _data = MutableLiveData<LoginResponse>()
    val data: LiveData<LoginResponse> = _data
//    init {
//        login()
//    }
    fun login(provider: String, providerId: String, email: String, name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.login(provider, providerId, email, name).body()
            getLog(response.toString())
            UserPreferences.userId = response!!.jwtToken
//            UserPreferences.userName = response!!.name
//            UserPreferences.profileImage = response!!.profileImage
        }
    }
}