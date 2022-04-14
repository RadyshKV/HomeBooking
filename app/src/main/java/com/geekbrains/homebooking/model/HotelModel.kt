package com.geekbrains.homebooking.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class HotelModel(

    @Expose
    val id: String,

    @Expose
    val name: String,

    @Expose
    val type: String?,

    @Expose
    val url: String?,

    @Expose
    val address: String?,

    @Expose
    val geo: Geo,

   // @Expose
   // val desc: String,

    @Expose
    val images: List<String?>,

    ) : Serializable

data class Geo(
    @Expose
    val country_id: List<Int?>,

    @Expose
    val region_id: List<Int?>,

    @Expose
    val resort_id: List<Int?>,

    @Expose
    val city_id: List<Int?>,

    @Expose
    val lat: String?,

    @Expose
    val lng: String?,
) : Serializable

