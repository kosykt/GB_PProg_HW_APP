package com.example.mytimer.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.gb_pprog.domain.MyTimerUseCase
import com.example.mytimer.ui.timerfragment.TimerViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import ru.kosykt.utils.di.scopes.TimerScope
import ru.kosykt.utils.di.viewmodelsfactory.ViewModelKey

@Module
interface TimerModule {

    @TimerScope
    @Binds
    @IntoMap
    @ViewModelKey(TimerViewModel::class)
    fun bindTimerViewModel(timerViewModel: TimerViewModel): ViewModel

    companion object {

        @TimerScope
        @Provides
        fun provideMyTimerUseCase(): MyTimerUseCase {
            return MyTimerUseCase()
        }

        @TimerScope
        @Provides
        fun provideTimerSubcomponentProvider(application: Application): TimerSubcomponentProvider{
            return (application as TimerSubcomponentProvider)
        }
    }
}