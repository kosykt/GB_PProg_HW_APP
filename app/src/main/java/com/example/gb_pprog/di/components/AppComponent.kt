package com.example.gb_pprog.di.components

import com.example.gb_pprog.di.modules.*
import com.example.gb_pprog.di.viewmodels.ViewModelFactory
import com.example.gb_pprog.di.viewmodels.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        RoomModule::class,
        RetrofitModule::class,
        DataModule::class,
        DomainModule::class,
        AppModule::class,
        MyTimerModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    fun injectViewModelFactory(): ViewModelFactory
}