package me.muchori.joseph.android_mvvm_login.viewmodels.auth

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.muchori.joseph.android_mvvm_login.data.network.NetworkResponse
import me.muchori.joseph.android_mvvm_login.data.network.api.UserApi
import me.muchori.joseph.android_mvvm_login.data.repository.userRepository.AuthRepository
import me.muchori.joseph.android_mvvm_login.model.user.User
import retrofit2.Response

class AuthViewModel(
    private val repository: AuthRepository,
    application: Application
) : AndroidViewModel(application) {

    var loginResponse: MutableLiveData<NetworkResponse<User>> = MutableLiveData()

    private fun updateUserDetails(user: User) = viewModelScope.launch(Dispatchers.IO) {
        repository.updateUserDetails(user)
    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        userLoginSafeCal(email, password)
    }

    private suspend fun userLoginSafeCal(email: String, password: String) {
        loginResponse.value = NetworkResponse.Loading()
        try {
            val response = repository.userLogin(email, password)
            loginResponse.value = handleUserLoginResponse(response)

            val userData = loginResponse.value!!.data
            if (userData != null) {
                updateUserDetails(userData)
            }
        } catch (e: Exception) {
            loginResponse.value = NetworkResponse.Error("User not found")
        }
    }

    private fun handleUserLoginResponse(response: Response<User>): NetworkResponse<User>? {
        return when {
            response.message().toString().contains("timeout") -> {
                NetworkResponse.Error("Timeout")
            }
            response.code() == 403 -> {
                NetworkResponse.Error("Api is limited")
            }
            response.isSuccessful -> {
                val user = response.body()
                NetworkResponse.Success(user!!)
            }
            else -> {
                NetworkResponse.Error(response.message())
            }
        }
    }

    suspend fun logout(userApi: UserApi) = repository.logout(userApi)
}