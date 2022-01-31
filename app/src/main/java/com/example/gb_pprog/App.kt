package com.example.gb_pprog

import android.app.Application
import com.example.gb_pprog.di.AppComponent
import com.example.gb_pprog.di.AppModule
import com.example.gb_pprog.di.DaggerAppComponent

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}