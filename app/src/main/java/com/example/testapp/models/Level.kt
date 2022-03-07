package com.example.testapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "levels")
data class Level(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    var order: Int,
    var title:String,
    var description: String,
    var tasks: ArrayList<Task>,
    var isLocked: Boolean = true,
    var isConfirmed: Boolean = false
)

