package com.example.gb_pprog.mytimer

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyTimer {

    companion object {
        private const val BASE_START_VALUE: Long = 0
        private var running = false
        private var startingValue: Long = BASE_START_VALUE
    }

    fun setStartingValue(timeMillis: Long, isRunning: Boolean) {
        startingValue = timeMillis
        running = isRunning

    }

    fun getTimeMillis(): Flow<Long> {
        return startTimer()
    }

    private fun startTimer(): Flow<Long> {
        val timerStartedTime = System.currentTimeMillis()
        return flow {
            while (running) {
                delay(30)
                emit((System.currentTimeMillis() - timerStartedTime) + startingValue)
                Log.d("testTimer", "${(System.currentTimeMillis() - timerStartedTime) + startingValue}")
            }
        }
    }
}