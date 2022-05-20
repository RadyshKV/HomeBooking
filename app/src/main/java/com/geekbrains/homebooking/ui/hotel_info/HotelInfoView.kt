package com.geekbrains.homebooking.ui.hotel_info

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface HotelInfoView : MvpView {

    @AddToEndSingle
    fun setHotelAddress(hotelAddress: String?)

    @AddToEndSingle
    fun updateList()

    @AddToEndSingle
    fun loadImage(url: String?)
}