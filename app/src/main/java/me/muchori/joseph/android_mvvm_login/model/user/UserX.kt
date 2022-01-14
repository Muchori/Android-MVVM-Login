package me.muchori.joseph.android_mvvm_login.model.user


import com.google.gson.annotations.SerializedName

data class UserX(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("deletedAt")
    val deletedAt: Any,
    @SerializedName("email")
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("user_type")
    val userType: String
)