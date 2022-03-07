package com.example.testapp.di


import com.example.testapp.ui.LevelTasksFragment
import com.example.testapp.ui.LevelsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLevelsFragment(): LevelsFragment

    @ContributesAndroidInjector
    abstract fun contributeLevelTasksFragment(): LevelTasksFragment
}