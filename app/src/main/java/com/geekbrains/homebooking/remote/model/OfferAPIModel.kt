package com.geekbrains.homebooking.remote.model

import com.geekbrains.homebooking.BuildConfig
import com.geekbrains.homebooking.model.AppState
import com.geekbrains.homebooking.model.OfferModel
import com.google.gson.annotations.Expose
import java.io.Serializable

data class OfferAPIModel(
    @Expose
    val header: APIHeader,

    @Expose
    val request: HotelRequest,

    @Expose
    val is_success: Boolean,

    @Expose
    val response: List<OfferModel>,

    ) : Serializable

data class OfferRequest(
    @Expose
    val country_id: Int,

    @Expose
    val region_id: Int?,

    @Expose
    val resort_id: Int?,

    @Expose
    val city_id: Int?,

    @Expose
    val hotel_id: Int?,

    @Expose
    val date_begin: String,

    @Expose
    val date_end: String,

    @Expose
    val adult: Int,

    ) : Serializable


data class OfferRequestBody(
    @Expose
    val header: APIHeader,

    @Expose
    val request: OfferRequest,
) : Serializable

fun getOfferRequestBody(
    regionId: Int?,
    resortId: Int?,
    cityId: Int?,
    hotelId: Int?,
    dateBegin: String,
    dateEnd: String,
    adult: Int,
): OfferRequestBody {
    val apiKey: String = BuildConfig.TOKEN
    if (apiKey.isBlank()) {
        AppState.Error(Throwable("You need API key"))
    }
    return OfferRequestBody(
        APIHeader(apiKey, APIMethods.HOTEL_OFFERS.value),
        OfferRequest(
            APICountriesId.RUSSIA.value,
            regionId,
            resortId,
            cityId,
            hotelId,
            dateBegin,
            dateEnd,
            adult
        )
    )
}
