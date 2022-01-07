package me.muchori.joseph.android_mvvm_login.network.api

import me.muchori.joseph.android_mvvm_login.model.user.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {
    @FormUrlEncoded
    @POST("api/v1/rider/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<User>
}