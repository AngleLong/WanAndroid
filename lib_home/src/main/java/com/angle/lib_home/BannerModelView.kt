package com.angle.lib_home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.angle.lib_home.ui.home.HomeBannerBean
import com.youth.banner.Banner
import com.youth.banner.indicator.CircleIndicator

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class BannerModelView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val banner: Banner<HomeBannerBean, ImageAdapter>
    private var imageAdapter = ImageAdapter(arrayListOf())

    init {
        val rootView = View.inflate(context, R.layout.model_item_banner, this)
        banner = rootView.findViewById(R.id.banner)
        banner.setAdapter(imageAdapter)
        banner.indicator = CircleIndicator(context)
    }

    @ModelProp
    fun setShowData(item: List<HomeBannerBean>) {
        imageAdapter.setDatas(item)
    }
}