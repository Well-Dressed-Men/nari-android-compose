package com.welldressedmen.nari.preferences

import splitties.preferences.Preferences

object SurveyPreferences : Preferences("surveyState") {
    var sex by StringPref("surveySex", "")
}