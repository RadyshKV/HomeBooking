package com.geekbrains.homebooking.remote.model

import com.geekbrains.homebooking.BuildConfig
import com.geekbrains.homebooking.model.AppState
import com.geekbrains.homebooking.model.CityModel
import com.google.gson.annotations.Expose
import java.io.Serializable

data class CityAPIModel(

    @Expose
    val header: APIHeader,

    @Expose
    val request: CityRequest,

    @Expose
    val is_success: Boolean,

    @Expose
    val response: List<CityModel>,

    ) : Serializable

data class CityRequest(
    @Expose
    val country_id: Int,
): Serializable


data class CityBody(
    @Expose
    val header: APIHeader,

    @Expose
    val request: CityRequest,
): Serializable

fun getCityBody(): CityBody {
    val apiKey: String = BuildConfig.TOKEN
    if (apiKey.isBlank()) {
        AppState.Error(Throwable("You need API key"))
    }
    return CityBody(APIHeader(BuildConfig.TOKEN, APIMethods.GEO_CITY), CityRequest(3159))


}
