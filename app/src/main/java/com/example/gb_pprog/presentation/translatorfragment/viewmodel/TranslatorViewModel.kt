package com.example.gb_pprog.presentation.translatorfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.domain.GetTranslateUseCase
import com.example.gb_pprog.domain.SaveFavoriteUseCase
import com.example.gb_pprog.domain.model.DomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class TranslatorViewModel(
    private val getTranslateUseCase: GetTranslateUseCase,
    private val saveFavoriteUseCase: SaveFavoriteUseCase,
    private val networkStatus: NetworkStatus,
) : ViewModel() {

    private val _responseData = MutableLiveData<List<DomainModel>?>()
    val responseData: LiveData<List<DomainModel>?>
        get() = _responseData

    private val _isLoadingData = MutableLiveData<Boolean>()
    val loadingData: LiveData<Boolean>
        get() = _isLoadingData

    private val _errorText = MutableLiveData<String?>()
    val errorText: LiveData<String?>
        get() = _errorText

    fun saveFavorite(domainModel: DomainModel) {
        viewModelScope.launch(Dispatchers.IO) {
            saveFavoriteUseCase.execute(domainModel)
        }
    }

    fun getTranslate(word: String) {
        if (word.isBlank()) {
            refreshData(loading = false, error = null, response = null)
        } else {
            refreshData(loading = true, error = null, response = null)
            checkNetwork(word)
        }
    }

    private fun checkNetwork(word: String) {
        if (!networkStatus.isOnline()) {
            refreshData(loading = false, error = "Connection error", response = null)
        } else {
            getData(word)
        }
    }

    private fun getData(word: String) {
        viewModelScope.launch {
            getTranslateUseCase.execute(word).flowOn(Dispatchers.IO)
                .collect {
                    refreshData(loading = false, error = null, response = it)
                }
        }
    }

    private fun refreshData(loading: Boolean, error: String?, response: List<DomainModel>?) {
        _isLoadingData.postValue(loading)
        _errorText.postValue(error)
        _responseData.postValue(response)
    }
}

