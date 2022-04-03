package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import io.reactivex.rxjava3.core.Single

interface HotelsRepository {

    fun getHotels(cityModel: CityModel): Single<List<HotelModel>>
}
