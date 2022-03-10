package com.example.gb_pprog.presentation.translatorfragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.GetTranslateUseCase
import com.example.gb_pprog.domain.SaveFavoriteUseCase
import javax.inject.Inject

class TranslatorViewModelFactory @Inject constructor(
    private val getTranslateUseCase: GetTranslateUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TranslatorViewModel::class.java)) {
            return TranslatorViewModel(
                getTranslateUseCase,
                saveFavoriteUseCase,
                getAllFavoritesUseCase,
                deleteFavoriteUseCase
            ) as T
        } else {
            throw RuntimeException("TranslatorViewModelFactory: ViewModel class - $modelClass")
        }
    }
}