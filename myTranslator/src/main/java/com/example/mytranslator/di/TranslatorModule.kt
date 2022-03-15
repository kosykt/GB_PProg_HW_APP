package com.example.mytranslator.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.gb_pprog.di.scopes.TranslatorScope
import com.example.gb_pprog.di.viewmodelsfactory.ViewModelKey
import com.example.gb_pprog.domain.*
import com.example.mytranslator.ui.favoritefragment.FavoriteViewModel
import com.example.mytranslator.ui.translatorfragment.TranslatorViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
interface TranslatorModule {

    @TranslatorScope
    @Binds
    @IntoMap
    @ViewModelKey(TranslatorViewModel::class)
    fun bindTranslatorViewModel(vm: TranslatorViewModel): ViewModel

    @TranslatorScope
    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    fun bindFavoriteViewModel(vm: FavoriteViewModel): ViewModel

    companion object {

        @TranslatorScope
        @Provides
        fun provideGetTranslateUseCase(
            domainRepository: DomainRepository
        ): GetTranslateUseCase {
            return GetTranslateUseCase(domainRepository)
        }

        @TranslatorScope
        @Provides
        fun provideGetAllFavoritesUseCase(
            domainRepository: DomainRepository
        ): GetAllFavoritesUseCase {
            return GetAllFavoritesUseCase(domainRepository)
        }

        @TranslatorScope
        @Provides
        fun provideSaveFavoriteUseCase(
            domainRepository: DomainRepository
        ): SaveFavoriteUseCase {
            return SaveFavoriteUseCase(domainRepository)
        }

        @TranslatorScope
        @Provides
        fun provideDeleteFavoriteUseCase(
            domainRepository: DomainRepository
        ): DeleteFavoriteUseCase {
            return DeleteFavoriteUseCase(domainRepository)
        }

        @TranslatorScope
        @Provides
        fun provideTranslatorProvider(application: Application): TranslatorProvider {
            return (application as TranslatorProvider)
        }
    }
}