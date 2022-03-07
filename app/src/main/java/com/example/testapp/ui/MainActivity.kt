package com.example.testapp.ui

import android.os.Bundle
import com.example.testapp.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity: DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}