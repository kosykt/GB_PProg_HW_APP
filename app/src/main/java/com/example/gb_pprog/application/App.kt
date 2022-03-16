package com.example.gb_pprog.application

import android.app.Application
import com.example.gb_pprog.di.components.AppComponent
import com.example.gb_pprog.di.components.DaggerAppComponent
import com.example.gb_pprog.di.modules.AppModule
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import com.example.mytranslator.di.TranslatorSubcomponent
import com.example.mytimer.di.TimerSubcomponent
import com.example.mytimer.di.TimerSubcomponentProvider

class App : Application(), TimerSubcomponentProvider, TranslatorSubcomponentProvider {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    private var timerSubcomponent: TimerSubcomponent? = null
    private var translatorSubcomponent: TranslatorSubcomponent? = null

    override fun initTimerSubcomponent() = appComponent.provideTimerSubcomponent().also {
        if (timerSubcomponent == null) {
            timerSubcomponent = it
        }
    }

    override fun destroyTimerSubcomponent() {
        timerSubcomponent = null
    }

    override fun initTranslatorSubcomponent() = appComponent.provideTranslatorSubcomponent().also {
        if (translatorSubcomponent == null) {
            translatorSubcomponent = it
        }
    }

    override fun destroyTranslatorSubcomponent() {
        translatorSubcomponent = null
    }
}