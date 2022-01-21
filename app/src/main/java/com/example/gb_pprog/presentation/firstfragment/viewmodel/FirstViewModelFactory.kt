package com.example.gb_pprog.presentation.firstfragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.domain.SearchWordUseCase

class FirstViewModelFactory(
    private val searchWordUseCase: SearchWordUseCase,
    private val networkStatus: NetworkStatus
) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstViewModel::class.java)) {
            return FirstViewModel(searchWordUseCase, networkStatus) as T
        } else {
            throw RuntimeException("FirstViewModelFactory: ViewModel class - $modelClass")
        }
    }
}