package com.example.mytranslator.ui.favoritefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    val favoriteWords: StateFlow<List<FavoriteModel>> = getAllFavoritesUseCase.execute()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    fun deleteFavorite(model: FavoriteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase.execute(model)
        }
    }
}