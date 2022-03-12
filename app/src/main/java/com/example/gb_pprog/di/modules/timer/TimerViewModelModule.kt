package com.example.gb_pprog.di.modules.timer

import androidx.lifecycle.ViewModel
import com.example.gb_pprog.di.scopes.TimerScope
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelKey
import com.example.gb_pprog.ui.timerfragment.TimerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface TimerViewModelModule {

    @TimerScope
    @Binds
    @IntoMap
    @ViewModelKey(TimerViewModel::class)
    fun bindTimerViewModel(timerViewModel: TimerViewModel): ViewModel
}