package com.angle.lib_home.ui.home

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.angle.lib_home.*

/**
 * 主页的控制器
 */
class HomePagerListController(private val itemClickListener: () -> Unit) :
    PagingDataEpoxyController<Data>(
        diffingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler()
    ) {

    /**
     * 设置BannerList数据,设置后刷新
     */
    var bannerList: List<HomeBannerBean>? = arrayListOf()
        set(value) {
            field = value
            requestModelBuild()
        }


    override fun addModels(models: List<EpoxyModel<*>>) {

        bannerList?.let {
            bannerModelView {
                id("home Banner")
                showData(it)
            }
        }

        super.addModels(models)
    }

    override fun buildItemModel(currentPosition: Int, item: Data?): EpoxyModel<*> {
        return if (item == null) {
            throw IllegalStateException("didn't use place holder")
        } else {
            HomeMyViewModel_()
                .id("item ${item.id}")
                .showData(item)
                .itemClickListener(itemClickListener)
        }
    }
}