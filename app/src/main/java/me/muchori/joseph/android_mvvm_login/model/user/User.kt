package me.muchori.joseph.android_mvvm_login.model.user


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("status")
    val status: String
)