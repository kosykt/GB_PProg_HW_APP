package com.example.gb_pprog.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val hw4PresentationModule = module {

    viewModel<com.example.gb_pprog.mytimer.TimerViewModel> {
        com.example.gb_pprog.mytimer.TimerViewModel(myTimer = get<com.example.gb_pprog.mytimer.MyTimer>())
    }

    single<com.example.gb_pprog.mytimer.MyTimer> {
        com.example.gb_pprog.mytimer.MyTimer()
    }
}