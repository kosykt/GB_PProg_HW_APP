package com.example.mytranslator.ui.favoritefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private var _favoriteWords = MutableStateFlow<List<FavoriteModel>>(emptyList())
    val favoriteWords: StateFlow<List<FavoriteModel>>
        get() = _favoriteWords

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFavoritesUseCase.execute()
                .collect {
                    _favoriteWords.value = it
                }
        }
    }

    fun deleteFavorite(model: FavoriteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase.execute(model)
        }
    }
}