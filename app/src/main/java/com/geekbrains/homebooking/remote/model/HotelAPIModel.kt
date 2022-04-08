package com.geekbrains.homebooking.remote.model

import com.geekbrains.homebooking.BuildConfig
import com.geekbrains.homebooking.model.AppState
import com.geekbrains.homebooking.model.HotelModel
import com.google.gson.annotations.Expose
import java.io.Serializable

data class HotelAPIModel(
    @Expose
    val header: APIHeader,

    @Expose
    val request: HotelRequest,

    @Expose
    val is_success: Boolean,

    @Expose
    val response: List<HotelModel>,

    ) : Serializable

data class HotelRequest(
    @Expose
    val country_id: Int,

    @Expose
    val region_id: Int?,

    @Expose
    val resort_id: Int?,

    @Expose
    val city_id: Int?,

    @Expose
    val limit: Int,

    @Expose
    val offset: Int,

    @Expose
    val room_options: RoomOptions?,
) : Serializable

data class RoomOptions(
    @Expose
    val connecting_room: Boolean,

    @Expose
    val for_disabled: Boolean,
) : Serializable


data class HotelRequestBody(
    @Expose
    val header: APIHeader,

    @Expose
    val request: HotelRequest,
) : Serializable

fun getHotelRequestBody(regionId: Int?, resortId: Int?, cityId: Int?, limit: Int, offset: Int): HotelRequestBody {
    val apiKey: String = BuildConfig.TOKEN
    if (apiKey.isBlank()) {
        AppState.Error(Throwable("You need API key"))
    }
    return HotelRequestBody(APIHeader(apiKey, APIMethods.HOTEL_DESC.value), HotelRequest(APICountriesId.RUSSIA.value, regionId, resortId, cityId, limit, offset, room_options = null))
}