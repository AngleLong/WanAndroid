package com.angle.lib_home

import com.airbnb.epoxy.EpoxyController
import com.angle.lib_home.ui.home.Data
import com.angle.lib_home.ui.home.HomeBannerBean

/**
 * TypedEpoxyController  指定类型的控制器
 * EpoxyController 默认的控制器
 * AsyncEpoxyController 后台运行的控制器
 */
class HomeController : EpoxyController() {

    /**
     * 设置BannerList数据,设置后刷新
     */
    var bannerList: List<HomeBannerBean>? = arrayListOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    /**
     * 设置列表数据,设置后刷新
     */
    var itemList: List<Data>? = arrayListOf()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {

        bannerList?.let {
            bannerModelView {
                id("home Banner")
                showData(it)
            }
        }

        itemList?.let {
            it.forEach { item ->
                homeMyView {
                    id("item ${item.id}")
                    showData(item)
                }
            }
        }
    }
}