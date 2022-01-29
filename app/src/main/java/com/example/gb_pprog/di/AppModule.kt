package com.example.gb_pprog.di

import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.GetTranslateUseCase
import com.example.gb_pprog.domain.SaveFavoriteUseCase
import com.example.gb_pprog.presentation.favoritefragment.viewmodel.FavoriteViewModel
import com.example.gb_pprog.presentation.translatorfragment.viewmodel.TranslatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<TranslatorViewModel> {
        TranslatorViewModel(
            getTranslateUseCase = get<GetTranslateUseCase>(),
            networkStatus = get<NetworkStatus>(),
            saveFavoriteUseCase = get<SaveFavoriteUseCase>()
        )
    }

    viewModel<FavoriteViewModel> {
        FavoriteViewModel(
            getAllFavoritesUseCase = get<GetAllFavoritesUseCase>()
        )
    }
}