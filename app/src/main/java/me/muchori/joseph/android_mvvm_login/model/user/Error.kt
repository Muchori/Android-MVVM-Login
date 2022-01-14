package me.muchori.joseph.android_mvvm_login.model.user


import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("error")
    val error: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("statusCode")
    val statusCode: Int
)