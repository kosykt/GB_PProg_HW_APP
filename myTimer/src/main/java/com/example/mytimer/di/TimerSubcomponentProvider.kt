package com.example.mytimer.di

interface TimerSubcomponentProvider {

    fun initTimerSubcomponent(): TimerSubcomponent

    fun destroyTimerSubcomponent()
}