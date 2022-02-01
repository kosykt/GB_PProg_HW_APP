package com.example.gb_pprog

import android.app.Application
import com.example.gb_pprog.di.appModule
import com.example.gb_pprog.di.dataModule
import com.example.gb_pprog.di.domainModule
import com.example.gb_pprog.di.retrofitModule
import com.example.gb_pprog.di.hw4PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    domainModule,
                    dataModule,
                    retrofitModule,
                    hw4PresentationModule,
                )
            )
        }
    }
}