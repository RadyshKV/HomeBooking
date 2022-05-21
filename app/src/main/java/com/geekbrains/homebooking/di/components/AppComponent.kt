package com.geekbrains.homebooking.di.components

import com.geekbrains.homebooking.di.modules.*
import com.geekbrains.homebooking.ui.account.AccountPresenter
import com.geekbrains.homebooking.ui.auth.LoginPresenter
import com.geekbrains.homebooking.ui.cities.CitiesPresenter
import com.geekbrains.homebooking.ui.hotel_info.HotelInfoPresenterFactory
import com.geekbrains.homebooking.ui.hotels.HotelsPresenterFactory
import com.geekbrains.homebooking.ui.main.MainActivity
import com.geekbrains.homebooking.ui.main.MainPresenter
import com.geekbrains.homebooking.ui.register.RegisterPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CacheModule::class,
        CiceroneModule::class,
        ContextModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun mainPresenter(): MainPresenter
    fun citiesPresenter(): CitiesPresenter
    fun hotelsPresenterFactory(): HotelsPresenterFactory
    fun hotelInfoPresenterFactory(): HotelInfoPresenterFactory
    fun loginPresenter(): LoginPresenter
    fun registerPresenter(): RegisterPresenter
    fun accountPresenter(): AccountPresenter
}