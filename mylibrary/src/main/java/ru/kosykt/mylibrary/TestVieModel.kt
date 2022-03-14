package ru.kosykt.mylibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gb_pprog.domain.TestUseCase

class TestVieModel(private val testUseCase: TestUseCase) : ViewModel() {

    private var _myData = MutableLiveData<Int>()
    val myData: LiveData<Int>
        get() = _myData

    fun getNum() {
       _myData.value = testUseCase.execute()
    }
}