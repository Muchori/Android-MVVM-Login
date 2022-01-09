package me.muchori.joseph.android_mvvm_login.repository

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

    fun isFirstTimeLaunch(): Boolean = pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)


    companion object {
        private const val ONBOARD_KEY = "onBoard"
        private const val PRIVATE_MODE = 0
        private const val PREF_NAME = "app-prefs"
        private const val IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch"
    }
}