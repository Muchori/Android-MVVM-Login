package me.muchori.joseph.android_mvvm_login.viewmodels.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import me.muchori.joseph.android_mvvm_login.data.repository.userRepository.UserRepository

class HomeViewModel(
    private val repository: UserRepository,
    application: Application
) : AndroidViewModel(application)