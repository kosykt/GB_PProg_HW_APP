package com.example.mytranslator.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load("https:$url")
            .circleCrop()
            .into(container)
    }
}