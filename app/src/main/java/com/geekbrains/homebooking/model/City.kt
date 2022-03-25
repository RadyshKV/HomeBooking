package com.geekbrains.homebooking.model
import android.os.Parcelable
import com.geekbrains.homebooking.R
import kotlinx.parcelize.Parcelize


@Parcelize
data class City (
    val city: String,
    val numberOffers: Int,
    val id: Int
): Parcelable


fun getCities(): List<City> {
    return listOf(
        City("Москва", 50, R.drawable.moscow),
        City("Санкт-Петербург", 50, R.drawable.peter),
        City("Крым", 50, R.drawable.crimea),
        City("Сочи", 50, R.drawable.sochi),
    )
}