package com.example.gb_pprog.di

import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.domain.SearchWordUseCase
import com.example.gb_pprog.presentation.firstfragment.viewmodel.FirstViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<FirstViewModel> {
        FirstViewModel(
            searchWordUseCase = get<SearchWordUseCase>(),
            networkStatus = get<NetworkStatus>()
        )
    }
}