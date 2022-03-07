package com.example.testapp.di


import com.example.testapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class, MainModule::class, MainViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}