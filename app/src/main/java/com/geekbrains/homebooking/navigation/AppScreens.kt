package com.geekbrains.homebooking.navigation

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.ui.account.AccountFragment
import com.geekbrains.homebooking.ui.auth.LoginFragment
import com.geekbrains.homebooking.ui.cities.CitiesFragment
import com.geekbrains.homebooking.ui.hotel_info.HotelInfoFragment
import com.geekbrains.homebooking.ui.hotels.HotelsFragment
import com.geekbrains.homebooking.ui.register.RegisterFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

interface AppScreens{
    fun citiesScreen(): FragmentScreen

    fun hotelsScreen(cityModel: CityModel?): FragmentScreen

    fun hotelInfoScreen(hotelModel: HotelModel): FragmentScreen

    fun loginScreen(): FragmentScreen

    fun registerScreen(): FragmentScreen

    fun accountScreen(): FragmentScreen
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

    override fun loginScreen() = FragmentScreen {
        LoginFragment()
    }

    override fun registerScreen() = FragmentScreen {
        RegisterFragment()
    }

    override fun accountScreen() = FragmentScreen {
        AccountFragment()
    }
}