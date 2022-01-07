package me.muchori.joseph.android_mvvm_login.model.user


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("payload")
    val payload: Payload,
    @SerializedName("user")
    val user: UserX
)