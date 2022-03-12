package com.example.gb_pprog.application

import android.app.Application
import com.example.gb_pprog.di.components.AppComponent
import com.example.gb_pprog.di.components.DaggerAppComponent
import com.example.gb_pprog.di.components.FavoriteSubcomponent
import com.example.gb_pprog.di.components.TimerSubcomponent
import com.example.gb_pprog.di.containers.FavoriteContainer
import com.example.gb_pprog.di.containers.TimerContainer
import com.example.gb_pprog.di.modules.singletons.AppModule

class App : Application(), TimerContainer, FavoriteContainer {

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

    var timerSubcomponent: TimerSubcomponent? = null
    var favoriteSubcomponent: FavoriteSubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    override fun initTimerSubcomponent() = appComponent.provideTimerSubcomponent().also {
        timerSubcomponent = it
    }

    override fun destroyTimerSubcomponent() {
        timerSubcomponent = null
    }

    override fun initFavoriteSubcomponent() = appComponent.provideFavoriteSubcomponent().also {
        favoriteSubcomponent = it
    }

    override fun destroyFavoriteSubcomponent() {
        favoriteSubcomponent = null
    }
}