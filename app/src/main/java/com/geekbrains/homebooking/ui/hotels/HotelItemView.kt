package com.geekbrains.homebooking.ui.hotels

import com.geekbrains.homebooking.ui.base.IItemView


interface HotelItemView : IItemView {
    fun setName(name: String)
    fun loadImage(url: String?)
    fun setType(type: String?)
    fun setOffers(offers: Int?)
    fun setMinCost(cost: Int?)

}