package com.example.testapp.di

import com.example.testapp.repositories.LevelsRepository
import com.example.testapp.room.AppDatabase
import com.example.testapp.room.LevelDao
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun provideLevelDao(appDatabase: AppDatabase): LevelDao {
        return appDatabase.levelDao()
    }


    @Provides
    fun provideLevelsRepository(levelDao: LevelDao): LevelsRepository {
        return LevelsRepository(levelDao)
    }
}