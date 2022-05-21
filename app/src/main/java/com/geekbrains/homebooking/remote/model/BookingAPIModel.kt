package com.geekbrains.homebooking.remote.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class BookingRequestBody(
    @Expose
    val m_offer_id: String,

): Serializable

data class DeleteBookingRequestBody(
    @Expose
    val booking: Booking,

    ): Serializable

data class Booking(
    @Expose
    val offer: String,

    ): Serializable
