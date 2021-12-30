package com.angle.wanandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.angle.lib_login.LoginActivity
import com.angle.lib_net.RetrofitFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var truck: Truck

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        truck.deliver()

        val mainViewModel = ViewModelProvider(this,
            ViewModelFactory(RetrofitFactory.getDefaultService(ConfigUtils.baseUrl,
                clazz = UserApi::class.java))).get(MainViewModel::class.java)

        Intent(this, LoginActivity::class.java).apply {
            startActivity(this)
        }

        mainViewModel.login("张三", "111111")
    }
}