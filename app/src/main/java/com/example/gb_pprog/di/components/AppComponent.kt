package com.example.gb_pprog.di.components

import com.example.gb_pprog.di.modules.singletons.*
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelFactory
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RoomModule::class,
        RetrofitModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    fun injectViewModelFactory(): ViewModelFactory
}