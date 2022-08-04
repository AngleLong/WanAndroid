package com.angle.wanandroid

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.angle.lib_login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

     private val mainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val mainViewModel = ViewModelProvider(
//            this,
//            ViewModelFactory(HiNet.createRetrofitApi(UserApi::class.java))
//        )[MainViewModel::class.java]

        Intent(this, LoginActivity::class.java).apply {
            startActivity(this)
        }

        mainViewModel.login("张三", "111111")
    }
}