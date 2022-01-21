package com.example.gb_pprog.di

import android.content.Context
import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.data.network.DataSourceNetwork
import com.example.gb_pprog.data.network.RetrofitService
import com.example.gb_pprog.data.repository.DataSourceRepository
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.domain.DomainRepository
import org.koin.dsl.module

val dataModule = module {

    single<NetworkStatus> {
        NetworkStatus(context = get<Context>())
    }

    single<DataSourceRepository> {
        DataSourceNetwork(retrofitService = get<RetrofitService>())
    }

    single<DomainRepository> {
        DomainRepositoryImpl(dataSource = get<DataSourceRepository>())
    }
}