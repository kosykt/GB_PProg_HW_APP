package com.example.gb_pprog.di.modules.singletons

import com.example.gb_pprog.data.database.AppDatabase
import com.example.gb_pprog.data.database.DatabaseRepositoryImpl
import com.example.gb_pprog.data.network.NetworkRepositoryImpl
import com.example.gb_pprog.data.network.RetrofitService
import com.example.gb_pprog.data.repository.DatabaseRepository
import com.example.gb_pprog.data.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideNetworkRepository(retrofitService: RetrofitService): NetworkRepository {
        return NetworkRepositoryImpl(retrofitService)
    }

    @Singleton
    @Provides
    fun provideDatabaseRepository(db: AppDatabase): DatabaseRepository {
        return DatabaseRepositoryImpl(db)
    }
}