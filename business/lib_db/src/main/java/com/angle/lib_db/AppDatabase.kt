package com.angle.lib_db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.angle.lib_common.utils.ApplicationUtils

@Database(entities = [MessageBean::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var appDatabase: AppDatabase? = null

        fun getAppDatabase(): AppDatabase {
            return appDatabase ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    ApplicationUtils.getInstance().newApplication,
                    AppDatabase::class.java, "appDatabase"
                ).build()
                appDatabase = instance
                instance
            }
        }
    }

    abstract fun messageDao(): MessageBeanDao
}