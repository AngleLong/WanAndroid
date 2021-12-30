package com.angle.lib_common.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import com.angle.lib_common.R

import android.view.LayoutInflater

import androidx.databinding.DataBindingUtil




abstract class BaseDialog(
    context: Context,
    @StyleRes themeResId: Int = R.style.loadingDialog,
    private val gravity: Int = Gravity.CENTER,
) :
    Dialog(context, themeResId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val binding: DialogOlaBookingConfirmedBinding = DataBindingUtil.inflate(LayoutInflater.from(
//            context), R.layout.dialog_ola_booking_confirmed, null, false)
//        setContentView(binding.getRoot())
        setContentView(getResId())

        initLocation()
    }

    open fun initLocation() {
        val params = window?.attributes
        params?.width = WindowManager.LayoutParams.WRAP_CONTENT
        params?.height = WindowManager.LayoutParams.WRAP_CONTENT
        params?.gravity = gravity
        window?.attributes = params
    }

    //创建布局
    @LayoutRes
    abstract fun getResId(): Int
}