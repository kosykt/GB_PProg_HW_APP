package ru.kosykt.utils.imageloader

import android.widget.ImageView

interface ImageLoader {

    fun loadInto(url: String, container: ImageView)
}