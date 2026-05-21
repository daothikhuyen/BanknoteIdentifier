package com.example.banknoteindentifier.ui.main

import android.app.Application
import com.example.banknoteindentifier.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger() // log ra logcat
            androidContext(this@MyApplication) // truyền context
            modules(appModule) // register dependency
        }
    }
}