package me.muchori.joseph.android_mvvm_login.network.api

import me.muchori.joseph.android_mvvm_login.model.user.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    @GET("api/v1/users/{id}")
    suspend fun getUser(
        @Path("id") id: String
    ): Response<User>
}