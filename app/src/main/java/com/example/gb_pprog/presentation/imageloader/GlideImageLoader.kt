package com.example.gb_pprog.presentation.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.gb_pprog.R

class GlideImageLoader : ImageLoader<ImageView> {

    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .placeholder(R.drawable.ic_place_holder)
            .load("https:$url")
            .circleCrop()
            .error(R.drawable.ic_error)
            .into(container)
    }
}