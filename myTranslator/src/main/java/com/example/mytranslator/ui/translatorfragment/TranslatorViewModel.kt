package com.example.mytranslator.ui.translatorfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.GetTranslateUseCase
import com.example.gb_pprog.domain.SaveFavoriteUseCase
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.domain.model.FavoriteModel
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TranslatorViewModel @Inject constructor(
    private val getTranslateUseCase: GetTranslateUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val translatorSubcomponentProvider: TranslatorSubcomponentProvider
) : ViewModel() {

    private val favoriteWords = MutableStateFlow<List<String>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFavoritesUseCase.execute()
                .collect { ListModels ->
                    favoriteWords.value = ListModels.map {
                        it.word
                    }
                }
        }
    }

    private val _translatorState = MutableLiveData<TranslatorState>()
    val translatorState: LiveData<TranslatorState> = _translatorState

    fun getTranslate(word: String) {
        _translatorState.value = TranslatorState.Loading
        when {
            word.isEmpty() -> {
                _translatorState.value = TranslatorState.Success(emptyList())
            }
            else -> {
                viewModelScope.launch(Dispatchers.IO) {
                    try {
                        _translatorState.postValue(
                            TranslatorState.Success(getTranslateUseCase.execute(word))
                        )
                    } catch (e: Exception) {
                        _translatorState.postValue(TranslatorState.Error(e.message.toString()))
                    }
                }
            }
        }
    }

    fun favoriteWordOperator(domainModel: DomainModel): Boolean {
        return if (favoriteWords.value.contains(domainModel.text)) {
            viewModelScope.launch(Dispatchers.IO) {
                deleteFavoriteUseCase.execute(
                    FavoriteModel(
                        word = domainModel.text,
                        translation = domainModel.meanings[0].translation.text
                    )
                )
            }
            false
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                saveFavoriteUseCase.execute(domainModel)
            }
            true
        }
    }

    fun checkIsFavorite(domainModel: DomainModel): Boolean {
        return favoriteWords.value.contains(domainModel.text)
    }

    override fun onCleared() {
        translatorSubcomponentProvider.destroyTranslatorSubcomponent()
        super.onCleared()
    }
}