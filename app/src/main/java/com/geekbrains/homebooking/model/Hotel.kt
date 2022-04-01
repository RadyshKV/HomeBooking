package com.geekbrains.homebooking.model
import android.os.Parcelable
import com.geekbrains.homebooking.R
import kotlinx.parcelize.Parcelize


@Parcelize
data class Hotel (
    val hotel: String,
    val numberOffers: Int,
    val id: Int
): Parcelable


fun getHotels(city: City): List<Hotel> {
    return listOf(
        Hotel("отель 1", 50, R.drawable.hotel_1),
        Hotel("отель 2", 50, R.drawable.hotel_2),
        Hotel("отель 3", 50, R.drawable.hotel_3),
        Hotel("отель 4", 50, R.drawable.hotel_4),
    )
}