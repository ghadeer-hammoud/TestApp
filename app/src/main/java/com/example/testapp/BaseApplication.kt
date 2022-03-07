package com.example.testapp

import com.example.testapp.di.DaggerAppComponent
import com.example.testapp.room.AppDatabase
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {

    val appDatabase by lazy { AppDatabase.getInstance(this) }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

}