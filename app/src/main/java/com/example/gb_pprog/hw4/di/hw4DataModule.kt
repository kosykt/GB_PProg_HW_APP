package com.example.gb_pprog.hw4.di

import com.example.gb_pprog.hw4.data.RepositoryImpl
import com.example.gb_pprog.hw4.data.timer.MyTimer
import com.example.gb_pprog.hw4.domain.Repository
import org.koin.dsl.module

val hw4DataModule = module {

    single<Repository> {
        RepositoryImpl(myTimer = get<MyTimer>())
    }

    single<MyTimer> {
        MyTimer()
    }
}