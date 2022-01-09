package me.muchori.joseph.android_mvvm_login.ui.onboarding.entity

import androidx.annotation.StringRes
import me.muchori.joseph.android_mvvm_login.R

enum class OnBoardingPage(
//    @StringRes val titleResource: Int,
    @StringRes val subTitleResource: Int,
    @StringRes val descriptionResource: Int,
    @StringRes val logoResource: Int,
) {
    ONE(R.string.onboarding_slide1_subtitle, R.string.onboarding_slide1_desc, R.raw.location),
    TWO(R.string.onboarding_slide2_subtitle, R.string.onboarding_slide2_desc, R.raw.rider),
    THREE(R.string.onboarding_slide3_subtitle, R.string.onboarding_slide1_desc, R.raw.driver)
}