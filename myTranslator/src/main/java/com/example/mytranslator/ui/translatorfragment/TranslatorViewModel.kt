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

    private var favoriteWords: List<String> = emptyList<String>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllFavoritesUseCase.execute()
                .collect { ListModels ->
                    favoriteWords = ListModels.map {
                        it.word
                    }
                }
        }
    }

    private val _responseData = MutableLiveData<List<DomainModel>?>()
    val responseData: LiveData<List<DomainModel>?>
        get() = _responseData

    private val _errorText = MutableLiveData<String?>()
    val errorText: LiveData<String?>
        get() = _errorText

    fun favoriteWordOperator(domainModel: DomainModel): Boolean {
        return if (favoriteWords.contains(domainModel.text)) {
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
        return favoriteWords.contains(domainModel.text)
    }

    fun getTranslate(word: String) {
        if (word.isBlank()) {
            refreshData()
        } else {
            refreshData()
            getData(word)
        }
    }

    private fun getData(word: String) {
        viewModelScope.launch {
            try {
                _responseData.value = getTranslateUseCase.execute(word)
            } catch (e: Exception) {
                refreshData(error = e.message, response = null)
            }
        }
    }

    private fun refreshData(error: String? = null, response: List<DomainModel>? = null) {
        _errorText.postValue(error)
        _responseData.postValue(response)
    }

    override fun onCleared() {
        translatorSubcomponentProvider.destroyTranslatorSubcomponent()
        super.onCleared()
    }
}