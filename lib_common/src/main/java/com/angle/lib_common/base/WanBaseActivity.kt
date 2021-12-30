package com.angle.lib_common.base

import androidx.databinding.ViewDataBinding
import com.angle.lib_common.dialog.LoadingDialog

abstract class WanBaseActivity<DB : ViewDataBinding> : BaseActivity<DB>() {

    private var loadDialog: LoadingDialog? = null

    fun showDialog() {

        if (loadDialog == null) {
            loadDialog = LoadingDialog(this)
        }

        loadDialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        }
    }

    fun dismissDialog() {
        loadDialog?.let {
            if (it.isShowing) {
                it.hide()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        loadDialog?.let {
            it.dismiss()
            null
        }
    }
}