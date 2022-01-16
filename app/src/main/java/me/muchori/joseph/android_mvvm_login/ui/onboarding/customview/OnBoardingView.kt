package me.muchori.joseph.android_mvvm_login.ui.onboarding.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.onboarding_view.view.*
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.adapters.OnBoardingPagerAdapter
import me.muchori.joseph.android_mvvm_login.data.repository.onboarding.OnBoardingPrefManager
import me.muchori.joseph.android_mvvm_login.ui.onboarding.core.setParallaxTransformation
import me.muchori.joseph.android_mvvm_login.ui.onboarding.entity.OnBoardingPage

class OnBoardingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val numberOfPages by lazy { OnBoardingPage.values().size }
    private val prefManager: OnBoardingPrefManager

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.onboarding_view, this, true)
        setUpSlider(view)
        addingButtonClickListeners()
        prefManager = OnBoardingPrefManager(view.context)
    }

    private fun addingButtonClickListeners() {
        nextBtn.setOnClickListener { navigateToNextSlide() }
        skipBtn.setOnClickListener { }
        startBtn.setOnClickListener { }
    }

//    private fun setFirstTimeLaunchToFalse() {
//        prefManager.isFirstTimeLaunch = true
//
//    }

    private fun navigateToNextSlide() {
        val nextSlidePos: Int = slider?.currentItem?.plus(1) ?: 0
        slider?.setCurrentItem(nextSlidePos, true)
    }

    private fun setUpSlider(view: View) {
        with(slider) {
            adapter = OnBoardingPagerAdapter()
            setPageTransformer { page, position ->
                setParallaxTransformation(page, position)
            }

            addSliderChangeListener()

            val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.page_indicator)
            wormDotsIndicator.setViewPager2(this)
        }
    }

    private fun addSliderChangeListener() {
        slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (numberOfPages > 1) {
                    val newProgress = (position + positionOffset) / (numberOfPages - 1)
                    onboardingRoot.progress = newProgress
                }
            }
        })
    }
}