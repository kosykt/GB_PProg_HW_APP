package com.example.gb_pprog.di.components

import com.example.gb_pprog.di.modules.timer.TimerModule
import com.example.gb_pprog.di.scopes.TimerScope
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelFactory
import dagger.Subcomponent

@TimerScope
@Subcomponent(
    modules = [TimerModule::class]
)
interface TimerSubcomponent {

    fun injectTimerViewModule(): ViewModelFactory
}