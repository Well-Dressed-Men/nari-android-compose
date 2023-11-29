package com.welldressedmen.nari.data.db.preferences

import splitties.preferences.Preferences

object SurveyPreferences : Preferences("surveyState") {
    var sex by StringPref("surveySex", "")
}