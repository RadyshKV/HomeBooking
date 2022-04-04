package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.remote.model.NetworkModel
import io.reactivex.rxjava3.core.Single

interface CitiesRepository {

    fun getCities(): Single<List<CityModel>>
}
