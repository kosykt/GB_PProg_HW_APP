package com.example.gb_pprog.di

import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.GetTranslateUseCase
import com.example.gb_pprog.domain.SaveFavoriteUseCase
import com.example.gb_pprog.presentation.favoritefragment.viewmodel.FavoriteViewModel
import com.example.gb_pprog.presentation.mainactivity.ActivityViewModel
import com.example.gb_pprog.presentation.translatorfragment.viewmodel.TranslatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<TranslatorViewModel> {
        TranslatorViewModel(
            getTranslateUseCase = get<GetTranslateUseCase>(),
            saveFavoriteUseCase = get<SaveFavoriteUseCase>(),
            getAllFavoritesUseCase = get<GetAllFavoritesUseCase>(),
            deleteFavoriteUseCase = get<DeleteFavoriteUseCase>()
        )
    }

    viewModel<FavoriteViewModel> {
        FavoriteViewModel(
            getAllFavoritesUseCase = get<GetAllFavoritesUseCase>(),
            deleteFavoriteUseCase = get<DeleteFavoriteUseCase>(),
        )
    }

    viewModel<ActivityViewModel> {
        ActivityViewModel(
            getAllFavoritesUseCase = get<GetAllFavoritesUseCase>()
        )
    }
}