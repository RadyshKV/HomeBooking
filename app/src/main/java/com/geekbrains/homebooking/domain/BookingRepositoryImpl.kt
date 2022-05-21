package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.AuthorizationState
import com.geekbrains.homebooking.model.BookingModel
import com.geekbrains.homebooking.model.OfferModel
import com.geekbrains.homebooking.remote.RetrofitService
import com.geekbrains.homebooking.remote.model.Booking
import com.geekbrains.homebooking.remote.model.BookingRequestBody
import com.geekbrains.homebooking.remote.model.DeleteBookingRequestBody
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class BookingRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
) : BookingRepository {
    override fun postBooking(offer: OfferModel): Single<BookingModel> {
        return retrofitService.bookingHotel(
            BookingRequestBody(offer.id),
            "Bearer " + AuthorizationState.token
        )
    }

    override fun deleteBooking(offer: OfferModel): Observable<Any> {
        return retrofitService.deleteBooking(
            DeleteBookingRequestBody(Booking(offer.m_offer_id.toString())),
            "Bearer " + AuthorizationState.token
        )
    }
}