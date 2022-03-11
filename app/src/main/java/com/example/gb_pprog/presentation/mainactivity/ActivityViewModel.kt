package com.example.gb_pprog.presentation.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val BASE_COUNT = 0

class ActivityViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
) : ViewModel() {

    private var _countOfFavoriteWords = MutableStateFlow<Int>(BASE_COUNT)
    val countOfFavoriteWords: StateFlow<Int>
        get() = _countOfFavoriteWords

    fun getCountOfFavoriteWords() {
        viewModelScope.launch {
            getAllFavoritesUseCase.execute().flowOn(Dispatchers.IO)
                .collect {
                    _countOfFavoriteWords.value = it.size
                }
        }
    }
}