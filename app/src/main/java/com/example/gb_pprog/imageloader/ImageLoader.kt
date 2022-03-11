package com.example.gb_pprog.imageloader

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}