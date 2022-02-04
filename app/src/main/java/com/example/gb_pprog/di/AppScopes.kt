package com.example.gb_pprog.di

import com.example.gb_pprog.domain.*
import com.example.gb_pprog.mytimer.MyTimer
import com.example.gb_pprog.mytimer.TimerFragment
import com.example.gb_pprog.mytimer.TimerViewModel
import com.example.gb_pprog.presentation.favoritefragment.FavoriteFragment
import com.example.gb_pprog.presentation.favoritefragment.viewmodel.FavoriteViewModel
import com.example.gb_pprog.presentation.mainactivity.ActivityViewModel
import com.example.gb_pprog.presentation.mainactivity.MainActivity
import com.example.gb_pprog.presentation.translatorfragment.TranslatorFragment
import com.example.gb_pprog.presentation.translatorfragment.viewmodel.TranslatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val mainActivityScope = module {
    scope(named<MainActivity>()) {
        viewModel {
            ActivityViewModel(getAllFavoritesUseCase = get<GetAllFavoritesUseCase>())
        }
    }
}

val translatorFragmentScope = module{
    scope(named<TranslatorFragment>()){
        scoped { GetTranslateUseCase(domainRepository = get<DomainRepository>()) }
        scoped { SaveFavoriteUseCase(domainRepository = get<DomainRepository>()) }
        viewModel {
            TranslatorViewModel(
                getTranslateUseCase = get<GetTranslateUseCase>(),
                saveFavoriteUseCase = get<SaveFavoriteUseCase>(),
                getAllFavoritesUseCase = get<GetAllFavoritesUseCase>(),
                deleteFavoriteUseCase = get<DeleteFavoriteUseCase>()
            )
        }
    }
}

val favoriteFragmentScope = module {
    scope(named<FavoriteFragment>()) {
        viewModel<FavoriteViewModel> {
            FavoriteViewModel(
                getAllFavoritesUseCase = get<GetAllFavoritesUseCase>(),
                deleteFavoriteUseCase = get<DeleteFavoriteUseCase>(),
            )
        }
    }
}

val timerFragmentScope = module {
    scope(named<TimerFragment>()) {
        scoped<MyTimer> { MyTimer() }
        viewModel<TimerViewModel> {
            TimerViewModel(myTimer = get<MyTimer>())
        }
    }
}