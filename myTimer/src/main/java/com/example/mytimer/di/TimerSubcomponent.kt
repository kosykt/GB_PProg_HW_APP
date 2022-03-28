package com.example.mytimer.di

import com.example.mytimer.ui.timerfragment.TimerFragment
import dagger.Subcomponent
import ru.kosykt.utils.di.scopes.TimerScope

@TimerScope
@Subcomponent(
    modules = [TimerModule::class]
)
interface TimerSubcomponent {

    fun inject(fragment: TimerFragment)
}