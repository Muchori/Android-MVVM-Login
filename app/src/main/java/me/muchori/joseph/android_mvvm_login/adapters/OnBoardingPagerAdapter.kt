package me.muchori.joseph.android_mvvm_login.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.onboarding_page_item.view.*
import me.muchori.joseph.android_mvvm_login.R
import me.muchori.joseph.android_mvvm_login.ui.onboarding.entity.OnBoardingPage

class OnBoardingPagerAdapter(
    private val onBoardingPageList: Array<OnBoardingPage> = OnBoardingPage.values()
) : RecyclerView.Adapter<PageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        return LayoutInflater.from(parent.context).inflate(
            PageViewHolder.layout, parent, false
        ).let { PageViewHolder(it) }
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(onBoardingPageList[position])
    }

    override fun getItemCount(): Int {
        return onBoardingPageList.size
    }
}

class PageViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    @SuppressLint("ResourceType")
    fun bind(onBoardingPage: OnBoardingPage) {
        val res = root.context.resources
//        root.titleTv?.text = res.getString(onBoardingPage.titleResource)
        root.subTitleTv?.text = res.getString(onBoardingPage.subTitleResource)
        root.descTV?.text = res.getString(onBoardingPage.descriptionResource)
//        root.img.setImageResource(onBoardingPage.logoResource)
        root.img.setAnimation(onBoardingPage.logoResource)
        root.img.playAnimation()
    }

    companion object {
        @LayoutRes
        val layout = R.layout.onboarding_page_item
    }
}
