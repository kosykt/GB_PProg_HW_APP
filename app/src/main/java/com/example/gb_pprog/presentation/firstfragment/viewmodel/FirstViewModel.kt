package com.example.gb_pprog.presentation.firstfragment.viewmodel

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

    fun getTranslate(word: String){
        searchWordUseCase.execute(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { dto ->
                _responseData.value = dto
            }
    }
}