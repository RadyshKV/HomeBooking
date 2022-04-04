package com.geekbrains.homebooking.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class HotelModel(

    @Expose
    val id: String,

    @Expose
    val name: String,

    @Expose
    val cityId: String,

    @Expose
    val address: String,

    @Expose
    val website: String,

    @Expose
    val email: String,

    @Expose
    val telephone: String,

    @Expose
    val coord_w: String,

    @Expose
    val coord_i: String,

    @Expose
    val rating: Int,

): Serializable

