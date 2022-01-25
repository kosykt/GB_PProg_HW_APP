package com.example.gb_pprog.hw4.data

import com.example.gb_pprog.hw4.data.timer.MyTimer
import com.example.gb_pprog.hw4.domain.Repository

class RepositoryImpl(
    private val myTimer: MyTimer
) : Repository {

    override fun start() {

    }

    override fun pause() {

    }

    override fun stop() {

    }

    override fun getStringTime(): String {
        return ""
    }
}