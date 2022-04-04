package com.geekbrains.homebooking.remote

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET


interface RetrofitService {

    @GET("/users")
    fun getCities(): Single<List<CityModel>>

    @GET()
    fun getHotels(): Single<List<HotelModel>>
}