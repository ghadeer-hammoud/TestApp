package com.example.testapp.room

import androidx.room.*
import com.example.testapp.models.Level
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {

    @Query("SELECT * FROM levels ORDER BY 'order'")
    fun getAllLevels(): Flow<List<Level>>

    @Query("SELECT * FROM levels WHERE id = :id")
    suspend fun getLevel(id: Int): Level

    @Query("UPDATE levels SET isConfirmed = 1 WHERE id = :order")
    suspend fun confirmLevel(order: Int): Int

    @Query("UPDATE levels SET isLocked = 0 WHERE id = :order")
    suspend fun unLockLevel(order: Int): Int


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(levels: ArrayList<Level>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevel(level: Level)

    @Update
    suspend fun updateLevel(level: Level)

    @Delete
    suspend fun deleteLevel(level: Level)
}