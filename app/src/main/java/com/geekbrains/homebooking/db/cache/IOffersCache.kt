package com.geekbrains.homebooking.db.cache

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.model.OfferModel
import io.reactivex.rxjava3.core.Single

internal interface IOffersCache {

    fun setOffers(offers: List<OfferModel>): Single<List<OfferModel>>

    fun getOffers(hotelModel: HotelModel): Single<List<OfferModel>>

    fun getOffers(cityModel: CityModel): Single<List<OfferModel>>

}