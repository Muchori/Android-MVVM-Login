package me.muchori.joseph.android_mvvm_login.data.repository.onboarding

import android.content.Context
import android.content.SharedPreferences

class OnBoardingPrefManager(
    _context: Context
) {

    private val pref: SharedPreferences =
        _context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = pref.edit()

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor.commit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun getString(key: String): String? {
        return pref.getString(key, null)
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean? {
        return pref.getBoolean(key, false)
    }

    fun isFirstTimeLaunch(): Boolean = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)


    companion object {
        const val PREF_NAME = "app-prefs"
        const val IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch"
    }
}