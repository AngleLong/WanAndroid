package com.angle.lib_image

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Glide的具体实现类
 */
class GlideImageLoader : ImageLoader {
    override fun displayImage(imageview: ImageView, url: String, placehUrl: Int) {
        Glide.with(imageview.context).load(url).placeholder(placehUrl).into(imageview)
    }
}