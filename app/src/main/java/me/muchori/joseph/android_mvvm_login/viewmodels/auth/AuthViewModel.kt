package me.muchori.joseph.android_mvvm_login.viewmodels.auth

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.muchori.joseph.android_mvvm_login.model.user.User
import me.muchori.joseph.android_mvvm_login.network.NetworkResponse
import me.muchori.joseph.android_mvvm_login.repository.userRepository.AuthRepository
import retrofit2.Response

class AuthViewModel(
    private val repository: AuthRepository,
    application: Application
) : AndroidViewModel(application) {

//    private val dataRepository = ProtoDataStoreRepository(application)

    var loginResponse: MutableLiveData<NetworkResponse<User>> = MutableLiveData()

//    fun updateUserDetails(user: User) = viewModelScope.launch(Dispatchers.IO) {
//        try {
//            repository.updateUserDetails(user)
//            Log.d("ProtoDataStore", user.toString())
//        } catch (t: Throwable){
//            throw  t
//        }
//    }

//    fun clear() = viewModelScope.launch {
//        dataRepository.clear()
//    }

    fun loginUser(email: String, password: String) = viewModelScope.launch {
        userLoginSafeCal(email, password)
    }

    private suspend fun userLoginSafeCal(email: String, password: String) {
        loginResponse.value = NetworkResponse.Loading()
        try {
            val response = repository.userLogin(email, password)
            loginResponse.value = handleUserLoginResponse(response)
        } catch (e: Exception) {
            loginResponse.value = NetworkResponse.Error("User not found")
        }
    }

    private fun handleUserLoginResponse(response: Response<User>): NetworkResponse<User>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResponse.Error("Timeout")
            }
            response.code() == 402 -> {
                return NetworkResponse.Error("Api is limited")
            }
            response.isSuccessful -> {
                try {
                    val user = response.body()
                    Log.d("ResponseBody ", user.toString())
                    return NetworkResponse.Success(user!!)
                } catch (e: Exception) {
                    throw e
                }
            }
            else -> {
                return NetworkResponse.Error(response.message())
            }
        }
    }
}