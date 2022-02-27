package com.example.gb_pprog.di

import com.example.myfirsttest.data.UseCaseRepositoryImpl
import com.example.myfirsttest.domain.GetListUseCase
import com.example.myfirsttest.domain.UseCaseRepository
import com.example.myfirsttest.presentation.MyFirstTestViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myFirstTestModule = module {

    viewModel<MyFirstTestViewModel> {
        MyFirstTestViewModel(
            getListUseCase = get<GetListUseCase>()
        )
    }

    factory<GetListUseCase> {
        GetListUseCase(useCaseRepository = get<UseCaseRepository>())
    }

    single<UseCaseRepository> {
        UseCaseRepositoryImpl()
    }
}