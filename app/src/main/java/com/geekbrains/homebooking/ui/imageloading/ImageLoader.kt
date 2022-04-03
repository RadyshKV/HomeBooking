package com.geekbrains.homebooking.ui.imageloading

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
}