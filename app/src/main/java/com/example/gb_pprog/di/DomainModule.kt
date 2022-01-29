package com.example.gb_pprog.di

import com.example.gb_pprog.domain.DomainRepository
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.GetTranslateUseCase
import com.example.gb_pprog.domain.SaveFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetTranslateUseCase> {
        GetTranslateUseCase(domainRepository = get<DomainRepository>())
    }

    factory<SaveFavoriteUseCase> {
        SaveFavoriteUseCase(domainRepository = get<DomainRepository>())
    }

    factory {
        GetAllFavoritesUseCase(domainRepository = get<DomainRepository>())
    }
}