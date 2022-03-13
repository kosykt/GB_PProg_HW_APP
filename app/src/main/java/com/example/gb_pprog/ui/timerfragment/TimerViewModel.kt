package com.example.gb_pprog.ui.timerfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gb_pprog.di.containers.TimerContainer
import com.example.gb_pprog.domain.MyTimerUseCase
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimerViewModel @Inject constructor(
    private val myTimer: MyTimerUseCase,
    private val timerContainer: TimerContainer,
) : ViewModel() {

    private var timeMillis: Long = BASE_VALUE
    private var timerIsRunning = false

    private val _ticker: MutableStateFlow<String> = MutableStateFlow(format(BASE_VALUE))
    val ticker: StateFlow<String>
        get() = _ticker

    fun start() {
        if (!timerIsRunning) {
            timerIsRunning = true
            myTimer.setStartingValue(timeMillis = timeMillis, isRunning = timerIsRunning)
            startJob()
        }
    }

    private fun startJob() {
        viewModelScope.launch {
            myTimer.getTimeMillis()
                .collect { millis ->
                    timeMillis = millis
                    _ticker.value = format(millis)
                }
        }
    }

    fun pause() {
        timerIsRunning = false
        myTimer.setStartingValue(timeMillis = timeMillis, isRunning = timerIsRunning)
    }

    fun stop() {
        timerIsRunning = false
        timeMillis = BASE_VALUE
        viewModelScope.coroutineContext.cancelChildren()
        _ticker.value = format(BASE_VALUE)
    }

    private fun format(timestamp: Long): String {
        val millisecondsFormatted = (timestamp % 1000).pad(3)
        val seconds = timestamp / 1000
        val secondsFormatted = (seconds % 60).pad(2)
        val minutes = seconds / 60
        val minutesFormatted = (minutes % 60).pad(2)
        val hours = minutes / 60
        return if (hours > 0) {
            val hoursFormatted = (minutes / 60).pad(2)
            "$hoursFormatted:$minutesFormatted:$secondsFormatted"
        } else {
            "$minutesFormatted:$secondsFormatted:$millisecondsFormatted"
        }
    }

    private fun Long.pad(desiredLength: Int) = this.toString().padStart(desiredLength, '0')

    override fun onCleared() {
        super.onCleared()
        timerContainer.destroyTimerSubcomponent()
    }

    companion object {
        private const val BASE_VALUE: Long = 0
    }
}