package com.example.gb_pprog.di

import com.example.gb_pprog.domain.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetTranslateUseCase> {
        GetTranslateUseCase(domainRepository = get<DomainRepository>())
    }

    factory<SaveFavoriteUseCase> {
        SaveFavoriteUseCase(domainRepository = get<DomainRepository>())
    }

    factory<GetAllFavoritesUseCase> {
        GetAllFavoritesUseCase(domainRepository = get<DomainRepository>())
    }

    factory<DeleteFavoriteUseCase> {
        DeleteFavoriteUseCase(domainRepository = get<DomainRepository>())
    }
}