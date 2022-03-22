package com.example.gb_pprog.imageloader

import android.widget.ImageView

interface ImageLoader {

    fun loadInto(url: String, container: ImageView)
}