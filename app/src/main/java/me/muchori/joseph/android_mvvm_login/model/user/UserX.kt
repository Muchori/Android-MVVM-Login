package me.muchori.joseph.android_mvvm_login.model.user


import com.google.gson.annotations.SerializedName

data class UserX(
    @SerializedName("id")
    val id: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: Any,
    @SerializedName("deleted_at")
    val deletedAt: Any,
    @SerializedName("is_active")
    val isActive: Boolean
)