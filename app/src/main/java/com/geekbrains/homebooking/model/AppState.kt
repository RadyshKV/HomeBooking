package com.geekbrains.homebooking.model

sealed class AppState {
    data class Success(val cityData: List<City>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
