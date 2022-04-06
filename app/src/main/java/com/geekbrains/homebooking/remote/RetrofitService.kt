package com.geekbrains.homebooking.remote

import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.remote.model.CityAPIModel
import com.geekbrains.homebooking.remote.model.CityBody
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface RetrofitService {

    @POST(".")
    fun getCities(@Body cityBody : CityBody): Single<CityAPIModel>

    @GET()
    fun getHotels(): Single<List<HotelModel>>


}