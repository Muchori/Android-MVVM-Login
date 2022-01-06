package me.muchori.joseph.android_mvvm_login.util

import java.util.regex.Pattern

class Util {
    companion object {
        fun isEmailValid(email: String): Boolean {
            val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{0,9}$"
            val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }
    }
}