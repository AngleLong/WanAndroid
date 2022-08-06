package com.angle.lib_home.ui.notifications

import com.angle.lib_db.AppDatabase
import javax.inject.Inject

class NotificationsRepository @Inject constructor() {
    /**
     * 提供数据的存储库,正常情况下应该是根据网络的qpi获取,但是这里我先根据推送存入本地的数据库,这里从数据库拉取
     */
    fun notificationListFlow() = AppDatabase.getAppDatabase().messageDao().getAllFlow()
    suspend fun notificationListBean() = AppDatabase.getAppDatabase().messageDao().getAllBean()
}