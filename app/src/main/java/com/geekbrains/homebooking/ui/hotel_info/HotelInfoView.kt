package com.geekbrains.homebooking.ui.hotel_info

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface HotelInfoView : MvpView {
    @AddToEndSingle
    fun setHotelName(hotelName: String?)

    @AddToEndSingle
    fun setHotelType(hotelType : String)
}