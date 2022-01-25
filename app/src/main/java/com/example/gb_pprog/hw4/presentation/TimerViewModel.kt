package com.example.gb_pprog.hw4.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.hw4.domain.TimerInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class TimerViewModel(
    private val interactor: TimerInteractor,
) : ViewModel() {

    private val _ticker = MutableStateFlow("0")
    val ticker: StateFlow<String>
        get() = _ticker

    fun start() {
        interactor.start()
        startJob()
    }

    private fun startJob() {
        viewModelScope.launch {
            interactor.getStringTimeRepresentation().flowOn(Dispatchers.Main)
                .collect {
                    _ticker.value = it
                }
        }
    }

    fun pause() {
        interactor.pause()
    }

    fun stop() {
        interactor.stop()
    }
}