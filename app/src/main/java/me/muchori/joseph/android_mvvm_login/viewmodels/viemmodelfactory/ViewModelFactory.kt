package me.muchori.joseph.android_mvvm_login.viewmodels.viemmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.muchori.joseph.android_mvvm_login.repository.UserRepository
import me.muchori.joseph.android_mvvm_login.viewmodels.profile.HomeViewModel

class ViewModelFactory(
    private val repository: UserRepository
): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}