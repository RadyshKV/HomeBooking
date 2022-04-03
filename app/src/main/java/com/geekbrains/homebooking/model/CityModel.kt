package com.geekbrains.homebooking.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class CityModel(
    @Expose
    val id: String,

    @Expose
    val name: String,

    @Expose
    val imageUrl: String,

) : Serializable