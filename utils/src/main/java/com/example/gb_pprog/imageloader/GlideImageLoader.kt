package com.example.gb_pprog.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : ImageLoader {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load("https:$url")
            .circleCrop()
            .into(container)
    }
}