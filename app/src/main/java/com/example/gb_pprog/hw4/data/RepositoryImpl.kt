package com.example.gb_pprog.hw4.data

import com.example.gb_pprog.hw4.data.timer.MyTimer
import com.example.gb_pprog.hw4.domain.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl(
    private val myTimer: MyTimer
) : Repository {

    override fun setTimerParams(timeMillis: Long, isRunning: Boolean) {
        myTimer.setStartingValue(timeMillis = timeMillis, isRunning = isRunning)
    }

    override fun getTime(): Flow<Long> = myTimer.getTimeMillis()

}