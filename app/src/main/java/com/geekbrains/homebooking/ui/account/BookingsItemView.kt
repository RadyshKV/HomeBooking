package com.geekbrains.homebooking.ui.account

import com.geekbrains.homebooking.ui.base.IItemView


interface BookingItemView : IItemView {
    fun setListener()
    fun setRoomName(roomName: String?)
    fun setAccName(accName: String?)
    fun setMealName(mealName: String?)
    fun setDateBegin(dateBegin: String?)
    fun setDateEnd(dateEnd: String?)
    fun setNights(nights: Int?)
    fun setPrice(price: Int?)
    fun setCurrency(currency: String?)
}