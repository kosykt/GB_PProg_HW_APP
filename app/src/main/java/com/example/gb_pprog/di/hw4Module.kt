package com.example.gb_pprog.di

import com.example.mytimer.MyTimer
import com.example.mytimer.TimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val hw4PresentationModule = module {

    viewModel<com.example.mytimer.TimerViewModel> {
        com.example.mytimer.TimerViewModel(myTimer = get<com.example.mytimer.MyTimer>())
    }

    single<com.example.mytimer.MyTimer> {
        com.example.mytimer.MyTimer()
    }
}