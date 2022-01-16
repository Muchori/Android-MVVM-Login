package me.muchori.joseph.android_mvvm_login.model.user


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val `data`: Data
)