package me.muchori.joseph.android_mvvm_login.viewmodels.profile

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import me.muchori.joseph.android_mvvm_login.repository.UserRepository
import me.muchori.joseph.android_mvvm_login.model.user.User
import retrofit2.Response

class HomeViewModel(private val repository: UserRepository) : ViewModel() {

    val userResponse: MutableLiveData<Response<User>> = MutableLiveData()

    fun getUser(id: String){
        viewModelScope.launch {
            val response = repository.getUser(id)
            userResponse.value = response
        }
    }

//    suspend fun saveId(id: String){
//        repository.saveId(id)
//    }
}