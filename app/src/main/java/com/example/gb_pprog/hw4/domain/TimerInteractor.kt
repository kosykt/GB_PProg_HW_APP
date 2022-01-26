package com.example.gb_pprog.hw4.domain

import kotlinx.coroutines.flow.Flow

class TimerInteractor(
    private val repository: Repository,
) {
    fun setParams(timeMillis: Long, isRunning: Boolean) =
        repository.setTimerParams(timeMillis = timeMillis, isRunning = isRunning)

    fun getStringTimeRepresentation(): Flow<Long> = repository.getStringTime()

}