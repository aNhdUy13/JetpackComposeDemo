package com.mvpvn.jetpackcomposedemo

import android.app.Application
import com.mvpvn.jetpackcomposedemo.di.DI

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        DI.initialize(this)
    }
}