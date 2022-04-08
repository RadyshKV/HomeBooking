package com.geekbrains.homebooking.model

import com.geekbrains.homebooking.R
import com.google.gson.annotations.Expose
import java.io.Serializable

data class CityModel(
    @Expose
    val id: Int?,

    @Expose
    val name: String?,

    @Expose
    val resort_id: Int?,

    @Expose
    val resort_name: String?,

    @Expose
    val region_id: Int?,

    @Expose
    val region_name: String?,
) : Serializable

fun getCities(): List<CityModel> {
    return listOf(
        CityModel(4400, "Москва", null, null, 15789406, "Москва"),
        CityModel(4962, "Санкт-Петербург", null, null, 15789407, "Санкт-Петербург"),
        CityModel(null, null, null, null, 10227, "Крым"),
        CityModel(null, null, 21, "Сочи", 4052, "Краснодарский край"),
    )
}

fun getCityImage(): List<Int> {
    return listOf(
        R.drawable.moscow,
        R.drawable.peter,
        R.drawable.crimea,
        R.drawable.sochi,
    )
}

fun getCityName(): List<String> {
    return listOf(
        "Москва",
        "Санкт-Петербург",
        "Крым",
        "Сочи",
    )
}