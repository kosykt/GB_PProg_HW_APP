package com.example.gb_pprog.hw4.domain

import kotlinx.coroutines.flow.Flow

class TimerInteractor(
    private val repository: Repository,
) {
    fun start() = repository.start()

    fun pause() = repository.pause()

    fun stop() = repository.stop()

    fun getStringTimeRepresentation(): Flow<String> = repository.getStringTime()
}