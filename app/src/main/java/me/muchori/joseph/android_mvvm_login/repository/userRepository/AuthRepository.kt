package me.muchori.joseph.android_mvvm_login.repository.userRepository

import me.muchori.joseph.android_mvvm_login.model.user.User
import me.muchori.joseph.android_mvvm_login.network.api.LoginApi
import retrofit2.Response

class AuthRepository(
    private val loginApi: LoginApi,
//    private val repository: ProtoDataStoreRepository
) {
    suspend fun userLogin(
        email: String,
        password: String,
    ): Response<User> {
        return loginApi.loginUser(email, password)
    }
//    suspend fun updateUserDetails(user: User){
//        repository.loginUpdateUserDetails(user)
//    }
}