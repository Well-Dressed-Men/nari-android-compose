package com.welldressedmen.nari.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("jwtToken")
    val jwtToken : String
)
