package com.geekbrains.homebooking.remote

import com.geekbrains.homebooking.remote.model.*
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface RetrofitService {

    @POST(".")
    fun getCities(@Body cityRequestBody: CityRequestBody): Single<CityAPIModel>

    @POST(".")
    fun getHotels(@Body hotelRequestBody: HotelRequestBody): Single<HotelAPIModel>

    @POST(".")
    fun getOffers(@Body offerRequestBody: OfferRequestBody): Single<OfferAPIModel>

}