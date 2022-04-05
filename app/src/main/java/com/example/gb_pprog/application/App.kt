package com.example.gb_pprog.application

import android.app.Application
import com.example.gb_pprog.di.components.AppComponent
import com.example.gb_pprog.di.components.DaggerAppComponent
import com.example.gb_pprog.di.modules.AppModule
import com.example.mytimer.di.TimerSubcomponent
import com.example.mytimer.di.TimerSubcomponentProvider
import com.example.mytranslator.di.TranslatorSubcomponent
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import ru.kosykt.githubusers.di.GithubSubcomponent
import ru.kosykt.githubusers.di.GithubSubcomponentProvider

class App : Application(), TimerSubcomponentProvider, TranslatorSubcomponentProvider,
    GithubSubcomponentProvider {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    private var timerSubcomponent: TimerSubcomponent? = null
    private var translatorSubcomponent: TranslatorSubcomponent? = null
    private var githubSubcomponent: GithubSubcomponent? = null

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

    override fun initSubcomponent() = appComponent.provideGithubSubcomponent().also {
        if (githubSubcomponent == null) {
            githubSubcomponent = it
        }
    }

    override fun destroySubcomponent() {
        githubSubcomponent = null
    }
}