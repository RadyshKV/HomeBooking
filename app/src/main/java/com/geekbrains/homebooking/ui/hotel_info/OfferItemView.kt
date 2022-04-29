package com.geekbrains.homebooking.ui.hotel_info

import com.geekbrains.homebooking.ui.base.IItemView

interface OfferItemView: IItemView  {

        fun setAccName(accName: String?)
//        fun loadImage(url: String?)
        fun setRoomName(roomName: String?)
        fun setMealName(mealName: String?)
        fun setTariffName(tariffName: String?)
        fun setNights(nights: Int?)
        fun setQuote(quote: Int?)
        fun setDateEnd(dateEnd: String?)
        fun setDateBegin(dateBegin: String?)

}