package com.example.gb_pprog.di.modules

import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}