package com.welldressedmen.nari.preferences

import splitties.preferences.Preferences

object LocationPreferences : Preferences("locationState") {
    var id: Int by IntPref("id", 1)
    var name: String by StringPref("name", "서울특별시 종로구 중구")
    var nx: Int by IntPref("nx", 60)
    var ny: Int by IntPref("ny", 127)
    var midLandCode: String by StringPref("midLandCode", "11B10101")
    var midTempCode: String by StringPref("midTempCode", "11B00000")
    var stationName: String by StringPref("stationName", "중구")
}