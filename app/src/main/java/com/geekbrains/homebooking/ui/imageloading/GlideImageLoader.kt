package com.geekbrains.homebooking.ui.imageloading

import android.widget.ImageView
import com.bumptech.glide.Glide

class GlideImageLoader : ImageLoader<ImageView> {

    override fun loadInto(url: String?, container: ImageView) {
        Glide.with(container.context)
            .asBitmap()
            .load(url ?: getDefaultImage())
            //.circleCrop()
            .into(container)
    }

    fun getDefaultImage(): String{
        return "https://thumb.cloud.mail.ru/weblink/thumb/xw1/NuNt/MqD3wEyvd"
    }
}