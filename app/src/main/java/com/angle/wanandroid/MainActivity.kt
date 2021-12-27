package com.angle.wanandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.angle.lib_net.RetrofitFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val mainViewModel = ViewModelProvider(this,
            ViewModelFactory(RetrofitFactory.getDefaultService(ConfigUtils.baseUrl,
                clazz = UserApi::class.java))).get(MainViewModel::class.java)


        mainViewModel.login("张三", "111111")
    }
}