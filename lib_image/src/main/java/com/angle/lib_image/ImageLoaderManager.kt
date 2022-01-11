package com.angle.lib_image

import android.widget.ImageView

class ImageLoaderManager : ImageLoader {

    companion object {
        fun getInstance(): ImageLoaderManager {
            return ImageLoaderManager()
        }
    }

    //默认用Glide实现
    private var imageLoaderImpl: ImageLoader = GlideImageLoader()

    fun setImageLoaderType(imageLoaderType: ImageLoader) {
        imageLoaderImpl = imageLoaderType
    }

    override fun displayImage(imageview: ImageView, url: String, placehUrl: Int) {
        imageLoaderImpl.displayImage(imageview, url, placehUrl)
    }
}