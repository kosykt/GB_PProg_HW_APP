package com.example.gb_pprog.di

import com.example.gb_pprog.data.database.AppDatabase
import com.example.gb_pprog.data.database.DatabaseRepositoryImpl
import com.example.gb_pprog.data.network.NetworkRepositoryImpl
import com.example.gb_pprog.data.network.RetrofitService
import com.example.gb_pprog.data.repository.DatabaseRepository
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.data.repository.NetworkRepository
import com.example.gb_pprog.domain.DomainRepository
import org.koin.dsl.module

val dataModule = module {

    single<NetworkRepository> {
        NetworkRepositoryImpl(retrofitService = get<RetrofitService>())
    }

    single<DatabaseRepository> {
        DatabaseRepositoryImpl(db = get<AppDatabase>())
    }

    single<DomainRepository> {
        DomainRepositoryImpl(
            network = get<NetworkRepository>(),
            database = get<DatabaseRepository>()
        )
    }
}