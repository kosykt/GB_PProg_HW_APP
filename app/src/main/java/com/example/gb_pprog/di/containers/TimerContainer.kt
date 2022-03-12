package com.example.gb_pprog.di.containers

import com.example.gb_pprog.di.components.TimerSubcomponent

interface TimerContainer {

    fun initTimerSubcomponent(): TimerSubcomponent

    fun destroyTimerSubcomponent()
}