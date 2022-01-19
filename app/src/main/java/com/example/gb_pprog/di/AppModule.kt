package com.example.gb_pprog.di

import com.example.gb_pprog.domain.SearchWordUseCase
import com.example.gb_pprog.presentation.firstfragment.viewmodel.FirstViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideViewModelFactory(searchWordUseCase: SearchWordUseCase): FirstViewModelFactory{
        return FirstViewModelFactory(searchWordUseCase)
    }
}