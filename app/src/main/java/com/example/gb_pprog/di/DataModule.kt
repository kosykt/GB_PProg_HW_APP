package com.example.gb_pprog.di

import com.example.gb_pprog.data.network.DataSourceNetwork
import com.example.gb_pprog.data.network.RetrofitService
import com.example.gb_pprog.data.repository.DataSourceRepository
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.domain.DomainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDataSourceRepository(retrofitService: RetrofitService): DataSourceRepository {
        return DataSourceNetwork(retrofitService)
    }

    @Provides
    @Singleton
    fun provideDomainRepository(dataSourceRepository: DataSourceRepository): DomainRepository {
        return DomainRepositoryImpl(dataSourceRepository)
    }
}