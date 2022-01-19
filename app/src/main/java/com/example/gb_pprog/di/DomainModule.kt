package com.example.gb_pprog.di

import com.example.gb_pprog.domain.DomainRepository
import com.example.gb_pprog.domain.SearchWordUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    @Singleton
    fun provideSearchWordUseCase(domainRepository: DomainRepository): SearchWordUseCase{
        return SearchWordUseCase(domainRepository)
    }
}