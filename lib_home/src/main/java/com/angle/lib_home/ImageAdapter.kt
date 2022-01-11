package com.angle.lib_home

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.youth.banner.adapter.BannerAdapter
import android.view.ViewGroup
import com.angle.lib_home.ui.home.HomeBannerBean
import com.angle.lib_image.ImageLoaderManager


class ImageAdapter(datas: List<HomeBannerBean>) :
    BannerAdapter<HomeBannerBean, ImageAdapter.BannerViewHolder>(datas) {

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): BannerViewHolder {
        val imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return BannerViewHolder(imageView)
    }

    override fun onBindView(holder: BannerViewHolder?, data: HomeBannerBean?, position: Int, size: Int) {
        data?.let {
            ImageLoaderManager.getInstance()
                .displayImage(holder?.imageView!!, it.imagePath, R.drawable.home_banner_default)
        }
    }


    inner class BannerViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
}
