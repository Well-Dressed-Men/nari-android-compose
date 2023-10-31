package com.welldressedmen.nari.data.remote.model.request

import com.google.gson.JsonObject

//data class Temp(
//    val provider : String,
//    val providerId : String,
//    val email : String,
//    val name : String
//)

data class LoginRequestBody (
    val profileObj : JsonObject
)
