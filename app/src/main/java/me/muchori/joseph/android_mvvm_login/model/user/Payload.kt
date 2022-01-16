package me.muchori.joseph.android_mvvm_login.model.user


import com.google.gson.annotations.SerializedName

data class Payload(
    @SerializedName("type")
    val type: String,
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String
)