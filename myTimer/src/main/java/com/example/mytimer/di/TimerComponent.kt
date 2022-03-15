package com.example.mytimer.di

import com.example.gb_pprog.di.scopes.TimerScope
import com.example.mytimer.ui.timerfragment.TimerFragment
import dagger.Component

@TimerScope
@Component(
    modules = [TimerModule::class]
)
interface TimerComponent {

    fun inject(fragment: TimerFragment)
}