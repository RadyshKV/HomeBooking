package com.geekbrains.homebooking.model

sealed class AppState {
    data class SuccessCity(val cityData: List<City>) : AppState()
    data class SuccessHotel(val hotelData: List<Hotel>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
