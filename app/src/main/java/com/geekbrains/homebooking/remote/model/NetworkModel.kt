package com.geekbrains.homebooking.remote.model

import com.geekbrains.homebooking.model.CityModel
import com.google.gson.annotations.Expose
import java.io.Serializable

data class NetworkModel(

    @Expose
    val header: Header,

    @Expose
    val request: Request,

    @Expose
    val is_success: Boolean,

    @Expose
    val response: List<CityModel>,

    ) : Serializable

data class Header(
    @Expose
    val token: String,

    @Expose
    val method: String,
): Serializable

data class Request(
    @Expose
    val country_id: Int,
): Serializable

data class CityBody(
    @Expose
    val header: Header,

    @Expose
    val request: Request,
): Serializable

fun getCityBody(): CityBody {
    return CityBody(Header("9ca2449d91382adee964b679e98c246d", "Geo.City"), Request(3159))
}