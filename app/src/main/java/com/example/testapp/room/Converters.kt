package com.example.testapp.room

import androidx.room.TypeConverter
import com.example.testapp.models.Task
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromStringToTask(value: String): Task {
        val type = object : TypeToken<Task>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromTaskToString(task: Task): String {
        return Gson().toJson(task)
    }


    @TypeConverter
    fun fromStringToList(value: String): ArrayList<Task> {
        val listType = object : TypeToken<ArrayList<Task>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(list: ArrayList<Task>): String {
        return Gson().toJson(list)
    }
}