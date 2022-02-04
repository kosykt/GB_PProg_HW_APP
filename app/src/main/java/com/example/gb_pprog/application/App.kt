package com.example.gb_pprog.application

import android.app.Application
import com.example.gb_pprog.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    mainActivityScope,
                    translatorFragmentScope,
                    favoriteFragmentScope,
                    timerFragmentScope,
                    domainModule,
                    dataModule,
                    retrofitModule,
                    databaseModule,
                )
            )
        }
    }
}