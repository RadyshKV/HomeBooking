package com.geekbrains.homebooking.remote

import com.geekbrains.homebooking.remote.model.CityAPIModel
import com.geekbrains.homebooking.remote.model.CityRequestBody
import com.geekbrains.homebooking.remote.model.HotelAPIModel
import com.geekbrains.homebooking.remote.model.HotelRequestBody
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface RetrofitService {

    @POST(".")
    fun getCities(@Body cityRequestBody: CityRequestBody): Single<CityAPIModel>

    @POST(".")
    fun getHotels(@Body hotelRequestBody: HotelRequestBody): Single<HotelAPIModel>


}