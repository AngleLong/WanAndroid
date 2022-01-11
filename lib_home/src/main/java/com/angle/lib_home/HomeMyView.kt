package com.angle.lib_home

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.angle.lib_home.ui.home.Data


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HomeMyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val showTv: TextView

    init {
        val rootView = View.inflate(context, R.layout.item_home_my, this)
        showTv = rootView.findViewById<TextView>(R.id.titleTv)
    }

    @ModelProp
    fun setShowData(data: Data) {
        showTv.text = data.title
    }
}