package com.example.gb_pprog.hw4.data.timer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class MyTimer {

    private var checker = STOP
    private var t = 0

    private val data: Flow<String> = flow {
        while (checker == START) {
            t++
            emit(t.toString())
            delay(1000)
            if (checker == STOP) {
                emit(t.toString())
            } else if (checker == PAUSE) {
                emit(t.toString())
            }
        }
    }


    fun running() {
        checker = START
    }

    fun paused() {
        checker = PAUSE
    }

    fun stopped() {
        t = 0
        checker = STOP
    }

    fun currentTime(): Flow<String> {
        return data
            .flowOn(Dispatchers.Default)
            .catch { e -> println(e.message) }
    }

    companion object {

        private const val START = "start"
        private const val PAUSE = "pause"
        private const val STOP = "stop"
    }
}