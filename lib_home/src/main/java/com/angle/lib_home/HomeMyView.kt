package com.angle.lib_home

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.angle.lib_home.databinding.ItemHomeMyBinding
import com.angle.lib_home.ui.home.Data

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class HomeMyView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private val itemHomeMyBinding: ItemHomeMyBinding =
        DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.item_home_my,
            this,
            true)

    @ModelProp
    fun setShowData(data: Data) {

        //设置标题
        itemHomeMyBinding.titleTv.text = data.title

        //设置作者
        if (data.author.isNotEmpty()) {
            itemHomeMyBinding.authorTv.text =
                String.format(resources.getString(R.string.home_item_author), data.author)
        }

        //设置
        itemHomeMyBinding.niceDateTv.text = data.niceDate
    }
}