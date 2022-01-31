package com.example.gb_pprog.hw4.di

import com.example.gb_pprog.hw4.domain.Repository
import com.example.gb_pprog.hw4.domain.TimerInteractor
import org.koin.dsl.module

val hw4DomainModule = module {

    factory<TimerInteractor> {
        TimerInteractor(repository = get<Repository>())
    }
}