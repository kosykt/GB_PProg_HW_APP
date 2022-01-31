package com.example.gb_pprog.di

import com.example.gb_pprog.data.network.DataSourceNetwork
import com.example.gb_pprog.data.repository.DataSourceRepository
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.domain.DomainRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface Binds {

    @Binds
    @Singleton
    fun bindDataSourceRepository(impl: DataSourceNetwork): DataSourceRepository

    @Binds
    @Singleton
    fun bindDomainRepository(impl: DomainRepositoryImpl): DomainRepository
}