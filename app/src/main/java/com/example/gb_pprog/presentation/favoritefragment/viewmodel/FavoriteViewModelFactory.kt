package com.example.gb_pprog.presentation.favoritefragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(
                getAllFavoritesUseCase,
                deleteFavoriteUseCase
            ) as T
        } else {
            throw RuntimeException("FavoriteViewModelFactory: ViewModel class - $modelClass")
        }
    }
}