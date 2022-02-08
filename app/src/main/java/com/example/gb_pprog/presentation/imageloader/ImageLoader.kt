package com.example.gb_pprog.presentation.imageloader

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}