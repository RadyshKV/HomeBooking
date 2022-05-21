package com.geekbrains.homebooking.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class OfferModel(

    @Expose
    val id: String,

    @Expose
    val m_offer_id: String?,

    @Expose
    val hotel_id: Int,

    val city_id: Int?,

    @Expose
    val price: Int?,

    @Expose
    val currency: String?,

    @Expose
    val acc_name: String?,

    @Expose
    val room_name: String?,

    @Expose
    val meal_name: String?,

    @Expose
    val meal_code: String?,

    @Expose
    val tariff_name: String?,

    @Expose
    val date_begin: String?,

    @Expose
    val date_end: String?,

    @Expose
    val nights: Int?,

    @Expose
    val quote: Int?,

    ) : Serializable