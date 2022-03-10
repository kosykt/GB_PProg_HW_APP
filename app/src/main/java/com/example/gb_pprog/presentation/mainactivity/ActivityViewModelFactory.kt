package com.example.gb_pprog.presentation.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import javax.inject.Inject

class ActivityViewModelFactory @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivityViewModel::class.java)) {
            return ActivityViewModel(
                getAllFavoritesUseCase
            ) as T
        } else {
            throw RuntimeException("ActivityViewModelFactory: ViewModel class - $modelClass")
        }
    }
}