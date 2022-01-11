package me.muchori.joseph.android_mvvm_login.repository.userRepository

import me.muchori.joseph.android_mvvm_login.model.user.User
import me.muchori.joseph.android_mvvm_login.network.api.UserApi
import me.muchori.joseph.android_mvvm_login.network.retrofit.WebServiceClient
import retrofit2.Response

class UserRepository {
    suspend fun getUser(id: String): Response<User> {
        return WebServiceClient.client.create(UserApi::class.java).getUser(id)
    }
}