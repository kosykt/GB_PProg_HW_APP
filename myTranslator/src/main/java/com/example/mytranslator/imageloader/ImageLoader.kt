package com.example.mytranslator.imageloader

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}