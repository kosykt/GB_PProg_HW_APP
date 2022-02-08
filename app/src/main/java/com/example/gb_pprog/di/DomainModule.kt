package com.example.gb_pprog.di

import com.example.gb_pprog.domain.*
import org.koin.dsl.module

val domainModule = module {

    single<GetAllFavoritesUseCase> {
        GetAllFavoritesUseCase(domainRepository = get<DomainRepository>())
    }

    single<DeleteFavoriteUseCase> {
        DeleteFavoriteUseCase(domainRepository = get<DomainRepository>())
    }
}