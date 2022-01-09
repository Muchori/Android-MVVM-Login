package me.muchori.joseph.android_mvvm_login.ui.onboarding.core

import android.view.View
import kotlinx.android.synthetic.main.onboarding_page_item.view.*

fun setParallaxTransformation(page: View, position: Float) {
    page.apply {
        val parallaxView = this.img
        when {
            position < -1 -> //[-Infinity, -1]
                // this page is way off screen to the left
                alpha = 1f
            position <= 1 -> { //[-1, 1]
                parallaxView.translationX = -position * (width / 2) // half the normal speed
            }
            else -> { //[1, +Infinity]
                //this page is way off screen to the right
                alpha = 1f
            }
        }
    }
}