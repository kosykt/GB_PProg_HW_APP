package com.example.gb_pprog.di

import com.example.gb_pprog.presentation.firstfragment.FirstFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AppModule::class,
    DomainModule::class,
    DataModule::class,
    RetrofitModule::class,
])
interface AppComponent {

    fun inject(firstFragment: FirstFragment)
}