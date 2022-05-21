package com.geekbrains.homebooking.ui.hotel_info

import com.geekbrains.homebooking.model.OfferModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface HotelInfoView : MvpView {

    @AddToEndSingle
    fun setHotelAddress(hotelAddress: String?)

    @AddToEndSingle
    fun updateList()

    @AddToEndSingle
    fun loadImage(url: String?)

    @AddToEndSingle
    fun showDialog(offerModel: OfferModel)

    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()

    @Skip
    fun showMessage()
}