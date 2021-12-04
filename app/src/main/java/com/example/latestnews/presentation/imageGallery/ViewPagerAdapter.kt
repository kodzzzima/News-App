package com.example.latestnews.presentation.imageGallery

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.latestnews.util.loadFromUrl
import javax.inject.Inject


class ViewPagerAdapter @Inject constructor(
    private val context: Context,
    private val imageUrls: Array<String>
) :
    PagerAdapter() {
    override fun getCount(): Int {
        return imageUrls.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.loadFromUrl(imageUrls[position])
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
