package com.example.shiftlabapplication

import android.app.Application
import com.example.shiftlabapplication.data.di.dataModule
import com.example.shiftlabapplication.di.appModule
import com.example.shiftlabapplication.features.join.di.joinModule
import com.example.shiftlabapplication.features.start.di.startModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            androidLogger(level = Level.DEBUG)
            modules(
                appModule,
                startModule,
                joinModule,
                dataModule,
            )
        }
    }
}