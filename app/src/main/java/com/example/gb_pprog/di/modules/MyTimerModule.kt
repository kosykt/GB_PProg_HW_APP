package com.example.gb_pprog.di.modules

import com.example.gb_pprog.mytimer.MyTimer
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyTimerModule {

    @Singleton
    @Provides
    fun provideMyTimer(): MyTimer{
        return MyTimer()
    }
}