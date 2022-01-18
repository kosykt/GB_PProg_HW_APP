package com.example.gb_pprog.presentation.firstfragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.domain.SearchWordUseCase
import java.lang.RuntimeException

class FirstViewModelFactory(
    private val searchWordUseCase: SearchWordUseCase
): ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FirstViewModel::class.java)) {
            return FirstViewModel(searchWordUseCase) as T
        } else {
            throw RuntimeException("FirstViewModelFactory: ViewModel class - $modelClass")
        }
    }
}