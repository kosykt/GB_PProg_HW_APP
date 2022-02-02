package com.example.gb_pprog.di

import com.example.gb_pprog.mytimer.MyTimer
import com.example.gb_pprog.mytimer.TimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val hw4Module = module {

    viewModel<TimerViewModel> {
        TimerViewModel(myTimer = get<MyTimer>())
    }

    single<MyTimer> {
        MyTimer()
    }
}