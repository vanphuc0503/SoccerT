package com.example.soccert.data.source.local

import com.example.soccert.data.source.AppPreferencesDataSource
import com.example.soccert.data.source.local.pref.AppPreferencesHelper
import com.example.soccert.utils.SharePreferencesConst.PREFS_COMPETITION_KEY
import com.example.soccert.utils.SharePreferencesConst.PREFS_LANGUAGE_KEY

class AppPreferencesLocalDataSource(
    private val appPreferencesHelper: AppPreferencesHelper
) : AppPreferencesDataSource {

    override fun setLanguage(languageValue: String) =
        appPreferencesHelper.put(PREFS_LANGUAGE_KEY, languageValue)

    override fun getLanguage() = appPreferencesHelper.get(PREFS_LANGUAGE_KEY, "")

    override fun setCountryID(countryID: String) = appPreferencesHelper.put(
        PREFS_COMPETITION_KEY, countryID
    )

    override fun getCountryID() = appPreferencesHelper.get(PREFS_COMPETITION_KEY, "")
}
