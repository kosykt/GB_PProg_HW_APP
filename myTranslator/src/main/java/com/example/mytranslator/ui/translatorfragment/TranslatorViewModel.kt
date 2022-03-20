package com.example.mytranslator.ui.translatorfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.domain.DeleteFavoriteUseCase
import com.example.gb_pprog.domain.GetAllFavoritesUseCase
import com.example.gb_pprog.domain.GetTranslateUseCase
import com.example.gb_pprog.domain.SaveFavoriteUseCase
import com.example.gb_pprog.domain.model.DomainModel
import com.example.gb_pprog.domain.model.FavoriteModel
import com.example.mytranslator.di.TranslatorSubcomponentProvider
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class TranslatorViewModel @Inject constructor(
    getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val getTranslateUseCase: GetTranslateUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
    private val translatorSubcomponentProvider: TranslatorSubcomponentProvider
) : ViewModel() {

    private val myCoroutine = CoroutineScope(Dispatchers.IO)
    private val favoriteWords: StateFlow<List<String>> = getAllFavoritesUseCase.execute()
        .map { list ->
            list.map {
                it.word
            }
        }
        .stateIn(
            scope = myCoroutine,
            started = SharingStarted.Eagerly,
            initialValue = emptyList()
        )

    private val _translatorState =
        MutableStateFlow<TranslatorState>(TranslatorState.Success(emptyList()))
    val translatorState: StateFlow<TranslatorState> = _translatorState.asStateFlow()

    fun getTranslate(word: String) {
        _translatorState.value = TranslatorState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            if (word.isEmpty()) {
                _translatorState.value = TranslatorState.Success(emptyList())
                viewModelScope.coroutineContext.cancelChildren()
            }
            getTranslateUseCase.execute(word)
                .distinctUntilChanged()
                .catch { e ->
                    _translatorState.value = TranslatorState.Error(e.message.toString())
                }.collectLatest { list ->
                    _translatorState.value = TranslatorState.Success(list)
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
        myCoroutine.coroutineContext.cancelChildren()
        super.onCleared()
    }
}