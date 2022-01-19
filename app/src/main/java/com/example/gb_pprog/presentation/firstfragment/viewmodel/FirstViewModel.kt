package com.example.gb_pprog.presentation.firstfragment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb_pprog.data.connectivity.NetworkStatus
import com.example.gb_pprog.domain.SearchWordUseCase
import com.example.gb_pprog.domain.model.DomainModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

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
        if (networkStatus.isOnline()) {
            _isLoadingData.value = true
            searchWordUseCase.execute(word)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { dto ->
                    //todo имеется очень редкий баг, появляется при частом вводе и последующем быстром стирании текста
                    if (word.isBlank()) {
                        _responseData.value = null
                        setError(false)
                    } else {
                        _responseData.value = dto
                        setError(dto.isEmpty())
                    }
                    _isLoadingData.value = false
                }
        }else{
            setError(true)
        }
    }

    private fun setError(error: Boolean) {
        when (error) {
            true -> _errorText.value = "Translation not found"
            false -> _errorText.value = null
        }
    }
}