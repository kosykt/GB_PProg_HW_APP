package com.example.gb_pprog.presentation.firstfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.domain.SearchWordUseCase
import com.example.gb_pprog.domain.model.DomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstViewModel(
    private val searchWordUseCase: SearchWordUseCase,
    private val networkStatus: NetworkStatus
) : ViewModel() {

    private val _responseData = MutableLiveData<List<DomainModel>>()
    val responseData: LiveData<List<DomainModel>>
        get() = _responseData

    private val _isLoadingData = MutableLiveData<Boolean>()
    val loadingData: LiveData<Boolean>
        get() = _isLoadingData

    private val _errorText = MutableLiveData<String>()
    val errorText: LiveData<String>
        get() = _errorText

    fun getTranslate(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (networkStatus.isOnline()) {
                _isLoadingData.postValue(true)
                when {
                    word.isBlank() -> {
                        _responseData.postValue(null)
                        _isLoadingData.postValue(false)
                    }
                    word.isNotBlank() -> {
                        _responseData.postValue(searchWordUseCase.execute(word))
                        _isLoadingData.postValue(false)
                        setError(false)
                    }
                }
            } else {
                setError(true)
            }
        }
    }

    private suspend fun setError(error: Boolean) {
        when (error) {
            true -> _errorText.postValue("Translation not found")
            false -> _errorText.postValue(null)
        }
    }
}