package com.angle.lib_common.dialog

import android.content.Context
import com.angle.lib_common.R
import com.angle.lib_common.base.BaseDialog

class LoadingDialog(context: Context) :BaseDialog(context) {
    override fun getResId(): Int  = R.layout.dialog_loading
}