package com.example.gb_pprog.hw4.domain

interface Repository {

    fun start()

    fun pause()

    fun stop()

    fun getStringTime(): String
}