package com.geekbrains.homebooking.ui.cities

import com.geekbrains.homebooking.ui.base.IItemView

interface CityItemView : IItemView {
    fun setName(name: String)
    fun loadImage(id: Int)

}