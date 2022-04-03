package com.geekbrains.homebooking.db.cache


import com.geekbrains.homebooking.model.CityModel
import io.reactivex.rxjava3.core.Single

interface ICitiesCache {

    fun setCities(cities: List<CityModel> ): Single<List<CityModel>>

    fun getCities(): Single<List<CityModel>>
}