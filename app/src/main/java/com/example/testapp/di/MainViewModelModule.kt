package com.example.testapp.di

import androidx.lifecycle.ViewModel
import com.example.testapp.viewmodels.LevelTasksViewModel
import com.example.testapp.viewmodels.LevelsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LevelsViewModel::class)
    abstract fun bindLevelsViewModel(levelsViewModel: LevelsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LevelTasksViewModel::class)
    abstract fun bindLevelTasksViewModel(levelTasksViewModel: LevelTasksViewModel): ViewModel
}