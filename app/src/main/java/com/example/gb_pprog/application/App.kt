package com.example.gb_pprog.application

import android.app.Application
import com.example.gb_pprog.di.components.AppComponent
import com.example.gb_pprog.di.components.DaggerAppComponent
import com.example.gb_pprog.di.modules.ContextModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
}