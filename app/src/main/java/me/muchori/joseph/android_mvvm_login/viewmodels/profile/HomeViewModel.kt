package me.muchori.joseph.android_mvvm_login.viewmodels.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import me.muchori.joseph.android_mvvm_login.model.user.User
import me.muchori.joseph.android_mvvm_login.repository.UserRepository
import retrofit2.Response

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    val userResponse: MutableLiveData<Response<User>> = MutableLiveData()

    fun getUser(id: String){
        viewModelScope.launch {
            val response = repository.getUser(id)
            userResponse.value = response
        }
    }
}