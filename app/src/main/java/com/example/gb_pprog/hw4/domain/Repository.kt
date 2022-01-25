package com.example.gb_pprog.hw4.domain

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun start()

    fun pause()

    fun stop()

    fun getStringTime(): Flow<String>
}