package com.angle.lib_db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MessageBeanDao {

    @Insert
    fun insertAll(vararg message: MessageBean)

    @Delete
    fun delete(message: MessageBean)

    @Query("Select * From message")
    fun getAllFlow(): Flow<List<MessageBean>>

    @Query("Select * From message")
    suspend fun getAllBean(): List<MessageBean>

}