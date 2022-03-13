package com.example.gb_pprog.di.modules.timer

import androidx.lifecycle.ViewModel
import com.example.gb_pprog.application.App
import com.example.gb_pprog.di.containers.TimerContainer
import com.example.gb_pprog.di.scopes.TimerScope
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelKey
import com.example.gb_pprog.domain.MyTimerUseCase
import com.example.gb_pprog.ui.timerfragment.TimerViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface TimerModule {

    @TimerScope
    @Binds
    @IntoMap
    @ViewModelKey(TimerViewModel::class)
    fun bindTimerViewModel(timerViewModel: TimerViewModel): ViewModel

    @TimerScope
    @Binds
    fun provideTimerContainer(app: App): TimerContainer

    companion object {

        @TimerScope
        @Provides
        fun provideMyTimerUseCase(): MyTimerUseCase {
            return MyTimerUseCase()
        }
    }
}