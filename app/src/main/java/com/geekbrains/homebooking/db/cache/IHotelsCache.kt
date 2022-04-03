package com.geekbrains.homebooking.db.cache

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import io.reactivex.rxjava3.core.Single

interface IHotelsCache {

    fun setHotels(hotels: List<HotelModel>): Single<List<HotelModel>>

    fun getHotels(cityModel: CityModel): Single<List<HotelModel>>
}