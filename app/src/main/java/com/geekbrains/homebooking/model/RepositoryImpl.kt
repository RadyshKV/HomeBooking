package com.geekbrains.weatherwithmvvm.model

import Repository
import com.geekbrains.homebooking.model.City
import com.geekbrains.homebooking.model.getCities


class RepositoryImpl : Repository {
    override fun getCityFromServer() = City("Москва", 50, 0)
    override fun getCityFromLocalStorage() = getCities()
}