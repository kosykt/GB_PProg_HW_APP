package com.example.gb_pprog.di.modules

import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.GetTranslateUseCase
import com.example.gb_pprog.domain.SaveFavoriteUseCase
import com.example.gb_pprog.presentation.translatorfragment.viewmodel.TranslatorViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideTranslatorViewModelFactory(
        getTranslateUseCase: GetTranslateUseCase,
        saveFavoriteUseCase: SaveFavoriteUseCase,
        getAllFavoritesUseCase: GetAllFavoritesUseCase,
        deleteFavoriteUseCase: DeleteFavoriteUseCase,
    ): TranslatorViewModelFactory {
        return TranslatorViewModelFactory(
            getTranslateUseCase,
            saveFavoriteUseCase,
            getAllFavoritesUseCase,
            deleteFavoriteUseCase
        )
    }
}