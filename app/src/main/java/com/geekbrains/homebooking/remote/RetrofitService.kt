package com.geekbrains.homebooking.remote

import com.geekbrains.homebooking.model.BookingModel
import com.geekbrains.homebooking.model.UserModel
import com.geekbrains.homebooking.model.UserToken
import com.geekbrains.homebooking.remote.model.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*


interface RetrofitService {

    @POST(".")
    fun getCities(@Body cityRequestBody: CityRequestBody): Single<CityAPIModel>

    @POST(".")
    fun getHotels(@Body hotelRequestBody: HotelRequestBody): Single<HotelAPIModel>

    @POST(".")
    fun getOffers(@Body offerRequestBody: OfferRequestBody): Single<OfferAPIModel>

    @Headers("Content-Type: application/json")
    @GET("http://46.17.248.74:58235/api/user/update")
    fun getUserData(@Header("Authorization") authHeader: String): Single<UserModel>

    @Headers("Content-Type: application/json")
    @POST("http://46.17.248.74:58235/api/user/login")
    fun loginUser(@Body userRequestBody: UserRequestBody): Single<UserToken>

    @Headers("Content-Type: application/json")
    @POST("http://46.17.248.74:58235/api/user/create")
    fun regUser(@Body registerRequestBody: RegisterRequestBody): Single<UserModel>


    @Headers("Content-Type: application/json")
    @GET("http://46.17.248.74:58235/api/hotels/list")
    fun getUserBookings(@Header("Authorization") authHeader: String): Single<List<BookingModel>>

    @Headers("Content-Type: application/json")
    @POST("http://46.17.248.74:58235/api/hotels/create")
    fun bookingHotel(
        @Body bookingRequestBody: BookingRequestBody,
        @Header("Authorization") authHeader: String
    ): Single<BookingModel>

    @Headers("Content-Type: application/json")
    @DELETE("http://46.17.248.74:58235/api/hotels/update")
    fun deleteBooking(
        @Body bookingRequestBody: DeleteBookingRequestBody,
        @Header("Authorization") authHeader: String
    ): Observable<Any>
}