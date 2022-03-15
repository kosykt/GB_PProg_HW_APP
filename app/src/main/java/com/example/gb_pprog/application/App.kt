package com.example.gb_pprog.application

import android.app.Application
import com.example.gb_pprog.di.components.AppComponent
import com.example.gb_pprog.di.components.DaggerAppComponent
import com.example.gb_pprog.di.modules.singletons.AppModule
import com.example.mytimer.di.DaggerTimerComponent
import com.example.mytimer.di.TimerComponent
import com.example.mytimer.di.TimerComponentProvider

class App : Application(), TimerComponentProvider {

    companion object {
        private var _instance: App? = null
        val instance
            get() = _instance!!
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    override fun getTimerComponent(): TimerComponent {
        return DaggerTimerComponent.builder().build()
    }
}