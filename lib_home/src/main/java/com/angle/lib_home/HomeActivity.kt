package com.angle.lib_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.angle.lib_common.base.BaseActivity
import com.angle.lib_router.HOME_HOME

@Route(path = HOME_HOME)
class HomeActivity : BaseActivity() {

    override fun configLayoutRes(): Int = R.layout.activity_home
}