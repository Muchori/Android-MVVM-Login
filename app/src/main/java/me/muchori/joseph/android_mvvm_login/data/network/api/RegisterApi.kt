package me.muchori.joseph.android_mvvm_login.data.network.api

import me.muchori.joseph.android_mvvm_login.model.user.UserX
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RegisterApi {

    @FormUrlEncoded
    @POST("api/v1/users/signup")
    suspend fun register(
        @Field("first_name") first_name: String,
        @Field("last_name") last_name: String,
        @Field("email") email: String,
        @Field("phone") phone: String,
        @Field("password") password: String,
    ): Response<UserX>
}