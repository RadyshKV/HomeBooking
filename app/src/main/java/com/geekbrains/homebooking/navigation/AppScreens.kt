package com.geekbrains.homebooking.navigation

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.ui.cities.CitiesFragment
import com.geekbrains.homebooking.ui.hotel_info.HotelInfoFragment
import com.geekbrains.homebooking.ui.hotels.HotelsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface AppScreens{
    fun citiesScreen(): FragmentScreen

    fun hotelsScreen(cityModel: CityModel?): FragmentScreen

    fun hotelInfoScreen(hotelModel: HotelModel): FragmentScreen
}


class AppScreensImpl: AppScreens {
    override fun citiesScreen() = FragmentScreen {
        CitiesFragment()
    }

    override fun hotelsScreen(cityModel: CityModel?) = FragmentScreen {
        HotelsFragment.newInstance(cityModel)
    }

    override fun hotelInfoScreen(hotelModel: HotelModel) = FragmentScreen {
        HotelInfoFragment.newInstance(hotelModel)
    }
}