package me.muchori.joseph.android_mvvm_login.data.network.api

import me.muchori.joseph.android_mvvm_login.model.user.User
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserApi {

    @FormUrlEncoded
    @POST("api/v1/users/login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<User>

    @POST("api/v1/users/logout")
    suspend fun logout(): ResponseBody
}