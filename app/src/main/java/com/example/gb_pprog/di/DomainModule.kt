package com.example.gb_pprog.di

import com.example.gb_pprog.domain.DomainRepository
import com.example.gb_pprog.domain.GetTranslateUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetTranslateUseCase> {
        GetTranslateUseCase(domainRepository = get<DomainRepository>())
    }
}