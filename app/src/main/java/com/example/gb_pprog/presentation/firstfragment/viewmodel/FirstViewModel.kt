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
        if (word.isBlank()) {
            _responseData.value = null
            _isLoadingData.value = false
            _errorText.value = null
        } else {
            _isLoadingData.value = true
            checkNetwork(word)
        }
    }

    private fun checkNetwork(word: String) {
        if (!networkStatus.isOnline()) {
            _errorText.value = "Connection error"
        } else {
            getData(word)
        }
    }

    private fun getData(word: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = searchWordUseCase.execute(word)
            if (data.isNullOrEmpty()) {
                _errorText.postValue("Translation not found")
                _isLoadingData.postValue(false)
                _responseData.postValue(null)
            } else {
                _errorText.postValue(null)
                _responseData.postValue(data)
                _isLoadingData.postValue(false)
            }
        }
    }
}

