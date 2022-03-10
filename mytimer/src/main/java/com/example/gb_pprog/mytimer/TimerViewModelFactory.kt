package com.example.gb_pprog.mytimer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class TimerViewModelFactory @Inject constructor(
    private val myTimer: MyTimer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TimerViewModel::class.java)) {
            return TimerViewModel(myTimer) as T
        } else {
            throw RuntimeException("TimerViewModelFactory: ViewModel class - $modelClass")
        }
    }
}