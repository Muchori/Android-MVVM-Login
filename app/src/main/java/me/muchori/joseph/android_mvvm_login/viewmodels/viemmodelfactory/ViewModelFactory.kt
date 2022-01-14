package me.muchori.joseph.android_mvvm_login.viewmodels.viemmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.muchori.joseph.android_mvvm_login.repository.userRepository.AuthRepository
import me.muchori.joseph.android_mvvm_login.viewmodels.auth.AuthViewModel

class ViewModelFactory(
    private val repository: AuthRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(repository, application = Application()) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}