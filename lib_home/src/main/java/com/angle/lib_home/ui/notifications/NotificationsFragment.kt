package com.angle.lib_home.ui.notifications

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.angle.lib_common.base.BaseFragment
import com.angle.lib_home.R
import com.angle.lib_home.databinding.FragmentNotificationsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NotificationsFragment : BaseFragment<FragmentNotificationsBinding>() {

    private val notificationsViewModel by lazy {
        ViewModelProvider(this)[NotificationsViewModel::class.java]
    }

    override fun configLayoutRes(): Int = R.layout.fragment_notifications

    override fun initEvent() {


        //插入
        notificationsViewModel.testAddNotificationItem()

        notificationsViewModel.requestNotificationList()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                notificationsViewModel.notificationListRv.collect {
                    //收集到相应的数据
                    Log.e("TAG", "requestNotificationList: 是否响应到回调1")
                }
            }
        }

    }

    override fun onResume() {
        super.onResume()
        //插入
        notificationsViewModel.testAddNotificationItem()
    }
}