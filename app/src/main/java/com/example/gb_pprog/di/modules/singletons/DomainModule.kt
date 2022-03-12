package com.example.gb_pprog.di.modules.singletons

import com.example.gb_pprog.data.repository.DatabaseRepository
import com.example.gb_pprog.data.repository.DomainRepositoryImpl
import com.example.gb_pprog.data.repository.NetworkRepository
import com.example.gb_pprog.domain.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {

    @Singleton
    @Provides
    fun provideDomainRepository(
        network: NetworkRepository,
        database: DatabaseRepository
    ): DomainRepository {
        return DomainRepositoryImpl(network, database)
    }

    @Singleton
    @Provides
    fun provideGetTranslateUseCase(
        domainRepository: DomainRepository
    ): GetTranslateUseCase {
        return GetTranslateUseCase(domainRepository)
    }

    @Singleton
    @Provides
    fun provideGetAllFavoritesUseCase(
        domainRepository: DomainRepository
    ): GetAllFavoritesUseCase {
        return GetAllFavoritesUseCase(domainRepository)
    }

    @Singleton
    @Provides
    fun provideSaveFavoriteUseCase(
        domainRepository: DomainRepository
    ): SaveFavoriteUseCase {
        return SaveFavoriteUseCase(domainRepository)
    }

    @Singleton
    @Provides
    fun provideDeleteFavoriteUseCase(
        domainRepository: DomainRepository
    ): DeleteFavoriteUseCase {
        return DeleteFavoriteUseCase(domainRepository)
    }
}