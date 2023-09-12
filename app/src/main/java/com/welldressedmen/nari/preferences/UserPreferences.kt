package com.welldressedmen.nari.preferences

import splitties.preferences.Preferences

object UserPreferences : Preferences("userState") {
//    var serverAccessToken by StringOrNullPref("serverAccessToken", null)
    var userId by StringPref("userId", "")
//    var userName by StringOrNullPref("userName", null)
//    var profileImage by StringOrNullPref("profileImage", null)

}