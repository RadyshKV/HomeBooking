package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.OfferModel
import io.reactivex.rxjava3.core.Single

interface OffersRepository {

    fun getOffers(
        cityModel: CityModel,
        dateBegin: String,
        dateEnd: String,
        adult: Int
    ): Single<List<OfferModel>>

}