package com.example.gb_pprog.di.modules.timer

import com.example.gb_pprog.application.App
import com.example.gb_pprog.di.containers.TimerContainer
import com.example.gb_pprog.di.scopes.TimerScope
import com.example.gb_pprog.domain.MyTimerUseCase
import dagger.Module
import dagger.Provides

@Module
class TimerModule {

    @TimerScope
    @Provides
    fun provideMyTimerUseCase(): MyTimerUseCase {
        return MyTimerUseCase()
    }

    @TimerScope
    @Provides
    fun provideTimerContainer(app: App): TimerContainer {
        return app
    }
}

