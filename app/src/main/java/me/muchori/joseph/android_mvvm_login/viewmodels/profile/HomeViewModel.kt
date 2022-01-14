package me.muchori.joseph.android_mvvm_login.viewmodels.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.muchori.joseph.android_mvvm_login.model.user.User
import me.muchori.joseph.android_mvvm_login.repository.userRepository.ProtoDataStoreRepository
import me.muchori.joseph.android_mvvm_login.repository.userRepository.UserRepository
import retrofit2.Response

class HomeViewModel(
    private val repository: UserRepository,
    application: Application
) : AndroidViewModel(application) {

    private val dataStoreRepository = ProtoDataStoreRepository(application)
    private val userResponse: MutableLiveData<Response<User>> = MutableLiveData()

//    val userData = dataStoreRepository.readProto.asLiveData()

    fun getUser(id: String) {
        viewModelScope.launch {
            val response = repository.getUser(id)
            userResponse.value = response
        }
    }
}