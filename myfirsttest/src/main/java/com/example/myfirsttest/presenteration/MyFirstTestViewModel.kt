package com.example.myfirsttest.presenteration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirsttest.domain.GetListUseCase
import com.example.myfirsttest.domain.UseCaseModel

class MyFirstTestViewModel(
    private val getListUseCase: GetListUseCase,
) : ViewModel() {

    private var _list = MutableLiveData<List<UseCaseModel>>()
    val list: LiveData<List<UseCaseModel>>
        get() = _list

    fun getList() {
        _list.value = getListUseCase.execute()
    }
}