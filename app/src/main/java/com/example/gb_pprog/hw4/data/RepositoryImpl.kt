package com.example.gb_pprog.hw4.data

import com.example.gb_pprog.hw4.data.timer.MyTimer
import com.example.gb_pprog.hw4.domain.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val myTimer: MyTimer
) : Repository {

    override fun start() {
        myTimer.running()
    }

    override fun pause() {
        myTimer.paused()
    }

    override fun stop() {
        myTimer.stopped()
    }

    override fun getStringTime(): Flow<String> = myTimer.currentTime()
}