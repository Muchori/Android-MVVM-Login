package me.muchori.joseph.android_mvvm_login.ui.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kotlinx.android.synthetic.main.onboarding_view.*
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.repository.onboarding.OnBoardingPrefManager
import me.muchori.joseph.android_mvvm_login.ui.MainActivity
import me.muchori.joseph.android_mvvm_login.viewmodels.SplashViewModel

class OnBoardingActivity : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()
        splashScreen.apply {
            setKeepVisibleCondition {
                splashViewModel.isLoading.value
            }
        }
        setContentView(R.layout.activity_on_boarding)
        startBtn.setOnClickListener {
            navigateToMain()
        }
        skipBtn.setOnClickListener {
            navigateToMain()
        }
    }

    private fun navigateToMain() {
        OnBoardingPrefManager(this).setFirstTimeLaunch(false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}