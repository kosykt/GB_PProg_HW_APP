package com.example.gb_pprog.hw4.domain

class TimerInteractor(
    private val repository: Repository,
) {
    fun start() = repository.start()

    fun pause() = repository.pause()

    fun stop() = repository.stop()

    fun getStringTimeRepresentation(): String = repository.getStringTime()
}