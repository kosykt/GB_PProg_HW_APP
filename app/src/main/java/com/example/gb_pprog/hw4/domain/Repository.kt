package com.example.gb_pprog.hw4.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun setTimerParams(timeMillis: Long, isRunning: Boolean)

    fun getTime(): Flow<Long>
}