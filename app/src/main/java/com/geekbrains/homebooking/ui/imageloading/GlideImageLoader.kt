package com.geekbrains.homebooking.ui.imageloading

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.geekbrains.homebooking.R

class GlideImageLoader : ImageLoader<ImageView> {

    override fun loadInto(url: String?, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url)
            .centerCrop()
            .error(R.drawable.ic_baseline_image_24)
            .into(container)
    }
}