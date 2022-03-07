package com.example.testapp.repositories

import com.example.testapp.models.Level
import com.example.testapp.room.LevelDao
import javax.inject.Inject

class LevelsRepository @Inject constructor(private val levelDao: LevelDao)  {

    fun getLevels() = levelDao.getAllLevels()

    suspend fun getLevel(id: Int) = levelDao.getLevel(id)

    suspend fun updateLevel(level: Level) = levelDao.updateLevel(level)

    suspend fun confirmLevel(levelOrder: Int): Int = levelDao.confirmLevel(levelOrder)

    suspend fun unLockLevel(levelOrder: Int): Int = levelDao.unLockLevel(levelOrder)
}