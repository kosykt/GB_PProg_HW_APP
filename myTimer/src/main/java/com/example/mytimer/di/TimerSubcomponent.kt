package com.example.mytimer.di

import com.example.gb_pprog.di.scopes.TimerScope
import com.example.mytimer.ui.timerfragment.TimerFragment
import dagger.Subcomponent

@TimerScope
@Subcomponent(
    modules = [TimerModule::class]
)
interface TimerSubcomponent {

    fun inject(fragment: TimerFragment)
}