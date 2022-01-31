package com.example.gb_pprog.di

import com.example.gb_pprog.presentation.firstfragment.viewmodel.FirstViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkStatusModule::class,
        RetrofitModule::class,
        Binds::class
    ]
)
interface AppComponent {

    fun injectFirstViewModelFactory(): FirstViewModelFactory
}