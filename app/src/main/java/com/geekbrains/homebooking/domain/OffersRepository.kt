package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import io.reactivex.rxjava3.core.Single

interface OffersRepository {
    fun getOffers(cityModel: CityModel, limit: Int, offset: Int, ): Single<List<HotelModel>>

}