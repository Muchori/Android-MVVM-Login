package me.muchori.joseph.android_mvvm_login.repository.userRepository

import me.muchori.joseph.android_mvvm_login.model.user.User
import me.muchori.joseph.android_mvvm_login.network.api.UserApi
import retrofit2.Response

class UserRepository(
    private val userApi: UserApi,
) {
    suspend fun getUser(id: String): Response<User> {
        return userApi.getUser(id)
    }
}