package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.BookingModel
import com.geekbrains.homebooking.model.OfferModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface BookingRepository {

    fun postBooking(offer: OfferModel):Single<BookingModel>

    fun deleteBooking(offerModel: OfferModel): Observable<Any>

}