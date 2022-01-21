package com.example.gb_pprog.di

import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.data.network.ApiHolder
import com.example.gb_pprog.data.network.DataSourceNetwork
import com.example.gb_pprog.data.network.RetrofitService
import com.example.gb_pprog.data.repository.DataSourceRepository
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.domain.DomainRepository
import org.koin.dsl.module

val dataModule = module {

    single<NetworkStatus> {
        NetworkStatus(context = get())
    }

    single<RetrofitService> {
        ApiHolder.retrofitService
    }

    single<DataSourceRepository> {
        DataSourceNetwork(retrofitService = get())
    }

    single<DomainRepository> {
        DomainRepositoryImpl(dataSource = get())
    }
}