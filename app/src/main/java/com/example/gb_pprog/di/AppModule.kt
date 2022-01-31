package com.example.gb_pprog.di

import android.content.Context
import com.example.gb_pprog.App
import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.domain.SearchWordUseCase
import com.example.gb_pprog.presentation.firstfragment.viewmodel.FirstViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Singleton
    @Provides
    fun appContext(): Context {
        return app
    }

    @Provides
    @Singleton
    fun provideViewModelFactory(
        searchWordUseCase: SearchWordUseCase,
        networkStatus: NetworkStatus
    ): FirstViewModelFactory{
        return FirstViewModelFactory(searchWordUseCase, networkStatus)
    }
}