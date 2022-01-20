package com.example.gb_pprog.di

import android.content.Context
import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.data.network.DataSourceNetwork
import com.example.gb_pprog.data.network.RetrofitService
import com.example.gb_pprog.data.repository.DataSourceRepository
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.domain.DomainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkStatusModule {

    @Provides
    @Singleton
    fun provideNetworkStatus(context: Context): NetworkStatus {
        return NetworkStatus(context)
    }
}