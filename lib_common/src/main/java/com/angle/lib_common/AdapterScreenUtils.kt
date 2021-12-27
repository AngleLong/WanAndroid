package com.angle.lib_common

import android.content.res.Resources
import android.util.DisplayMetrics
import androidx.annotation.NonNull
import com.angle.lib_common.base.BaseApplication
import java.lang.reflect.Field


object AdapterScreenUtils {

    private var sMetricsFields: MutableList<Field>? = null


    fun adaptWidth(@NonNull resources: Resources, designWidth: Int): Resources {
        val newXdpi: Float = resources.displayMetrics.widthPixels * 72f / designWidth
        applyDisplayMetrics(resources, newXdpi)
        return resources
    }


    fun adaptHeight(@NonNull resources: Resources, designHeight: Int): Resources {
        return adaptHeight(resources, designHeight, false)
    }

    fun adaptHeight(
        @NonNull resources: Resources,
        designHeight: Int,
        includeNavBar: Boolean,
    ): Resources {
        val screenHeight: Float = (resources.displayMetrics.heightPixels
                + if (includeNavBar) getNavBarHeight(resources) else 0) * 72f
        val newXdpi = screenHeight / designHeight
        applyDisplayMetrics(resources, newXdpi)
        return resources
    }

    private fun getNavBarHeight(@NonNull resources: Resources): Int {
        val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return if (resourceId != 0) {
            resources.getDimensionPixelSize(resourceId)
        } else {
            0
        }
    }

    @NonNull
    fun closeAdapt(@NonNull resources: Resources): Resources {
        val newXdpi: Float = Resources.getSystem().displayMetrics.density * 72f
        applyDisplayMetrics(resources, newXdpi)
        return resources
    }

    /**
     * Value of pt to value of px.
     *
     * @param ptValue The value of pt.
     * @return value of px
     */
    fun pt2Px(ptValue: Float): Int {
        val metrics: DisplayMetrics = BaseApplication.getInstance().resources.displayMetrics
        return (ptValue * metrics.xdpi / 72f + 0.5).toInt()
    }

    /**
     * Value of px to value of pt.
     *
     * @param pxValue The value of px.
     * @return value of pt
     */
    fun px2Pt(pxValue: Float): Int {
        val metrics: DisplayMetrics = BaseApplication.getInstance().resources.displayMetrics
        return (pxValue * 72 / metrics.xdpi + 0.5).toInt()
    }

    private fun applyDisplayMetrics(@NonNull resources: Resources, newXdpi: Float) {
        resources.displayMetrics.xdpi = newXdpi
        BaseApplication.getInstance().resources.displayMetrics.xdpi = newXdpi
        applyOtherDisplayMetrics(resources, newXdpi)
    }

    fun getPreLoadRunnable(): Runnable {
        return Runnable { preLoad() }
    }

    private fun preLoad() {
        applyDisplayMetrics(Resources.getSystem(), Resources.getSystem().getDisplayMetrics().xdpi)
    }

    private fun applyOtherDisplayMetrics(resources: Resources, newXdpi: Float) {
        if (sMetricsFields == null) {
            sMetricsFields = ArrayList()
            var resCls: Class<*> = resources.javaClass
            var declaredFields: Array<Field>? = resCls.declaredFields
            while (declaredFields != null && declaredFields.isNotEmpty()) {
                for (field in declaredFields) {
                    if (field.type.isAssignableFrom(DisplayMetrics::class.java)) {
                        field.isAccessible = true
                        val tmpDm = getMetricsFromField(resources, field)
                        if (tmpDm != null) {
                            sMetricsFields!!.add(field)
                            tmpDm.xdpi = newXdpi
                        }
                    }
                }
                resCls = resCls.superclass
                declaredFields = if (resCls != null) {
                    resCls.declaredFields
                } else {
                    break
                }
            }
        } else {
            applyMetricsFields(resources, newXdpi)
        }
    }

    private fun applyMetricsFields(resources: Resources, newXdpi: Float) {
        for (metricsField in sMetricsFields!!) {
            try {
                val dm = metricsField.get(resources) as DisplayMetrics
                dm.xdpi = newXdpi
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getMetricsFromField(resources: Resources, field: Field): DisplayMetrics? {
        return try {
            field[resources] as DisplayMetrics
        } catch (ignore: Exception) {
            null
        }
    }
}