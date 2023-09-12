package com.welldressedmen.nari.data.remote.request

import org.json.JSONObject

//data class Temp(
//    val provider : String,
//    val providerId : String,
//    val email : String,
//    val name : String
//)

data class LoginRequestBody (
    val profileObj : JSONObject
)
