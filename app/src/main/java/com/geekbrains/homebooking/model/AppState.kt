package com.geekbrains.homebooking.model

sealed class AppState {
    data class SuccessCity(val cityData: List<CityModel>) : AppState()
    data class SuccessHotel(val hotelData: List<HotelModel>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
