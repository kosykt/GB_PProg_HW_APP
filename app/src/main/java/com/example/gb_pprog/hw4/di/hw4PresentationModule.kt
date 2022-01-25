package com.example.gb_pprog.hw4.di

import com.example.gb_pprog.hw4.domain.TimerInteractor
import com.example.gb_pprog.hw4.presentation.TimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val hw4PresentationModule = module {

    viewModel<TimerViewModel> {
        TimerViewModel(interactor = get<TimerInteractor>())
    }
}