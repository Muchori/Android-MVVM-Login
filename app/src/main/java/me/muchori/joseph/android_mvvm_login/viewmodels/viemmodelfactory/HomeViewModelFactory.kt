package me.muchori.joseph.android_mvvm_login.viewmodels.viemmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.muchori.joseph.android_mvvm_login.repository.userRepository.UserRepository
import me.muchori.joseph.android_mvvm_login.viewmodels.profile.HomeViewModel
import me.muchori.joseph.android_mvvm_login.viewmodels.profile.ProfileViewModel

class HomeViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository, application = Application()) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(application = Application()) as T
            }
//            HomeViewModel(repository) as T
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}