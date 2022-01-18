package com.example.gb_pprog.presentation.firstfragment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb_pprog.domain.SearchWordUseCase
import com.example.gb_pprog.domain.model.DomainModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class FirstViewModel(
    private val searchWordUseCase: SearchWordUseCase
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
        _isLoadingData.value = true
        searchWordUseCase.execute(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { dto ->
                Log.d("testViewModel", dto.isNullOrEmpty().toString())
                if (word.isNotBlank()) {
                    _responseData.value = dto
                    setError(dto.isEmpty())
                } else {
                    _responseData.value = null
                    setError(false)
                }
                _isLoadingData.value = false
            }
    }

    private fun setError(error: Boolean) {
        when(error){
            true -> _errorText.value = "Translation not found"
            false -> _errorText.value = null
        }
    }
}