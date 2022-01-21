package com.example.gb_pprog.di

import com.example.gb_pprog.domain.DomainRepository
import com.example.gb_pprog.domain.SearchWordUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<SearchWordUseCase> {
        SearchWordUseCase(domainRepository = get<DomainRepository>())
    }
}