package com.angle.lib_db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class MessageBean(
    @ColumnInfo(name = "message")
    val message: String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}