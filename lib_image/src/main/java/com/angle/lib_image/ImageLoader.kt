package com.angle.lib_image

import android.widget.ImageView

/**
 * 图片接口层主要是为了隔离
 */
interface ImageLoader {

    fun displayImage(imageview: ImageView, url: String, placehUrl: Int)
}