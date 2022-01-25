package com.example.gb_pprog.hw4.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.hw4.domain.TimerInteractor
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TimerViewModel(
    private val interactor: TimerInteractor,
) : ViewModel() {

    private var job: Job? = null
    private var coroutineCheck: Boolean = false

    private val _ticker = MutableStateFlow("00:00:00")
    val ticker: StateFlow<String>
        get() = _ticker

    fun start() {
        if (job == null) {
            coroutineCheck = true
            startJob()
        }
        interactor.start()
    }

    private fun startJob() {
        viewModelScope.launch {
            while (coroutineCheck) {
                _ticker.value = interactor.getStringTimeRepresentation()
                delay(100)
            }
        }
    }

    fun pause() {
        interactor.pause()
        stopJob()
    }

    fun stop() {
        interactor.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        job = null
        coroutineCheck = false
    }

    private fun clearValue() {
        _ticker.value = "00:00:000"
    }
}