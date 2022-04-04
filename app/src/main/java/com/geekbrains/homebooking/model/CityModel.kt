package com.geekbrains.homebooking.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class CityModel(
    @Expose
    val id: String,

    @Expose
    val name: String,

    @Expose
    val resort_id: String,

    @Expose
    val resort_name: String,

    @Expose
    val region_id: String,

    @Expose
    val region_name: String,
) : Serializable