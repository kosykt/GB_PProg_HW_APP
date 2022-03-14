package com.example.gb_pprog.application

import android.app.Application
import com.example.gb_pprog.di.components.AppComponent
import com.example.gb_pprog.di.components.DaggerAppComponent
import com.example.gb_pprog.di.components.TimerSubcomponent
import com.example.gb_pprog.di.containers.TimerContainer
import com.example.gb_pprog.di.modules.singletons.AppModule
import ru.kosykt.mylibrary.DaggerTestComponent
import ru.kosykt.mylibrary.TestComponent
import ru.kosykt.mylibrary.TestProvider

class App : Application(), TimerContainer, TestProvider {

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

    private var timerSubcomponent: TimerSubcomponent? = null

    override fun initTimerSubcomponent() = appComponent.provideTimerSubcomponent().also {
        timerSubcomponent = it
    }

    override fun destroyTimerSubcomponent() {
        timerSubcomponent = null
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    override fun getTestComponent(): TestComponent {
        return  DaggerTestComponent.builder().build()
    }
}