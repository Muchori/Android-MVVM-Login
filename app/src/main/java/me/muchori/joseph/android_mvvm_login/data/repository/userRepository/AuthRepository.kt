package me.muchori.joseph.android_mvvm_login.data.repository.userRepository

import me.muchori.joseph.android_mvvm_login.data.network.api.UserApi
import me.muchori.joseph.android_mvvm_login.model.user.User
import retrofit2.Response

class AuthRepository(
    private val userApi: UserApi,
    private val repository: ProtoDataStoreRepository
) {
    suspend fun userLogin(
        email: String,
        password: String,
    ): Response<User> {
        return userApi.loginUser(email, password)
    }

    suspend fun updateUserDetails(user: User) {
        repository.loginUpdateUserDetails(user)
    }

    suspend fun logout(userApi: UserApi) {
        userApi.logout()
    }
}