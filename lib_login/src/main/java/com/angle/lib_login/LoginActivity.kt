package com.angle.lib_login

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.alibaba.android.arouter.facade.annotation.Route
import com.angle.lib_common.base.LoadingStatus.FINISH
import com.angle.lib_common.base.LoadingStatus.START
import com.angle.lib_common.base.WanBaseActivity
import com.angle.lib_common.utils.showToast
import com.angle.lib_login.databinding.ActivityLoginBinding
import com.angle.lib_router.LOGIN_LOGIN
import com.angle.lib_router.openHomePage
import com.gyf.immersionbar.ktx.immersionBar
import dagger.hilt.android.AndroidEntryPoint

@Route(path = LOGIN_LOGIN)
@AndroidEntryPoint
class LoginActivity : WanBaseActivity<ActivityLoginBinding>() {

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun configLayoutRes(): Int = R.layout.activity_login

    override fun initStatusBar() {
        immersionBar {
            statusBarDarkFont(true)
        }
    }

    override fun initConfig() {

        dataBinding?.loginOpt = LoginOpt()

        loginViewModel.loginModel.data.observe(this@LoginActivity) {
            Log.e("TAG", "正确: $it")

            //TODO 这里保存好用户的相关信息,直接跳转到主页去
            openHomePage()
        }

        loginViewModel.loginModel.error.observe(this@LoginActivity) {
            Log.e("TAG", "错误: $it")
            it.errorMsg?.showToast(this)
        }

        loginViewModel.loginModel.loadingStatus.observe(this) {
            when (it) {
                START -> {
                    showDialog()
                }
                FINISH -> {
                    dismissDialog()
                }
                else -> {
                    dismissDialog()
                }
            }
        }
    }

    inner class LoginOpt {
        private var isRegister = false

        fun registerClick() {
            //显示再次输入密码按钮,修改注册显示的文案,修改底部展示的文案
            dataBinding?.let {
                if (isRegister) {
                    //改变成登陆
                    it.againPsdTl.visibility = View.GONE
                    it.registerTv.text = getString(R.string.string_register_link)
                    it.loginBtn.text = getString(R.string.string_login)
                } else {
                    //改变成注册
                    it.againPsdTl.visibility = View.VISIBLE
                    it.registerTv.text = getString(R.string.string_login_link)
                    it.loginBtn.text = getString(R.string.string_register)
                }

                isRegister = !isRegister

                it.psdEt.setText("")
                it.userEt.setText("")
                it.againPsdEt.setText("")

                it.userEt.requestFocus()
            }
        }

        fun bottomClick() {
            dataBinding?.let {
                val userStr = it.userEt.text.toString()
                if (userStr.isEmpty()) {
                    "请正确填写用户名称".showToast(this@LoginActivity)
                    return
                }

                val psdStr = it.psdEt.text.toString()
                if (psdStr.isEmpty()) {
                    "请正确填写密码".showToast(this@LoginActivity)
                    return
                }

                val againPsdStr = it.againPsdEt.text.toString()
                if (isRegister && (psdStr.isEmpty() || againPsdStr != psdStr)) {
                    "请正确确认密码".showToast(this@LoginActivity)
                    return
                }

                if (isRegister) {
                    loginViewModel.register(userStr, psdStr, againPsdStr)
                } else {
                    loginViewModel.login(userStr, psdStr)
                }
            }
        }
    }
}