package com.example.gb_pprog.presentation.favoritefragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.model.FavoriteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private var _favoriteWords = MutableLiveData<List<FavoriteModel>>()
    val favoriteWords: LiveData<List<FavoriteModel>>
        get() = _favoriteWords

    fun getAll() {
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteWords.postValue(getAllFavoritesUseCase.execute())
        }
    }

    fun deleteFavorite(model: FavoriteModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteUseCase.execute(model)
        }
    }
}