package com.angle.lib_home.ui.notifications

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angle.lib_common.bean.BaseFlowModel
import com.angle.lib_db.MessageBean
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

/**
 * 消息的ViewModel
 */
@HiltViewModel
class NotificationsViewModel @Inject constructor(private val notificationsRepository: NotificationsRepository) :
    ViewModel() {

    private val TAG = "NotificationsViewModel"

    private val _notificationListRv = MutableStateFlow<List<MessageBean>>(emptyList())
    val notificationListRv = _notificationListRv

    private val stateFlow = MutableStateFlow<String>("")

    private val channel = Channel<String>(Channel.CONFLATED)

    private val notificationList = BaseFlowModel<List<MessageBean>>()

    /**
     * 请求消息列表数据
     */
    fun requestNotificationList() {
        //这里其实就是通过返回的Flow转换成新的Flow发送出去


        viewModelScope.launch {
            notificationsRepository.notificationListFlow()
                .collect {
                    Log.e(TAG, "requestNotificationList: 是否响应到回调")
                    _notificationListRv.emit(it)
                }

            notificationsRepository.notificationListFlow().collect{}

//            //将Flow转换成StateFlow进行监听
//            notificationsRepository.notificationListFlow().stateIn(viewModelScope)
        }


//        //如果这里面数据库返回的是一个实体类的情况下可以用下面的处理方案
//        loadDataStateFlow(notificationList, loader = {
//            notificationsRepository.notificationListBean()
//        })
    }

    fun testAddNotificationItem() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                com.angle.lib_db.AppDatabase.getAppDatabase().messageDao()
                    .insertAll(com.angle.lib_db.MessageBean("这是一条消息 ${Random.nextLong()}"))
            } catch (e: Exception) {
                Log.e(TAG, "testAddNotificationItem: 插入异常 $e")
            }
        }
    }
}