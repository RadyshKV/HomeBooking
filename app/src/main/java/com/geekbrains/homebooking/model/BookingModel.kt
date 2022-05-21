package com.geekbrains.homebooking.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class BookingModel(

    @Expose
    val offer: OfferModel,

    @Expose
    val user: Int,

    @Expose
    val email: String,

    @Expose
    val booking_id: String,

    @Expose
    val is_cancelled: Boolean
): Serializable

