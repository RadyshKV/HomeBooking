package com.geekbrains.homebooking.ui.hotel_info

import com.geekbrains.homebooking.model.HotelModel
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class HotelInfoPresenter @AssistedInject constructor(
    @Assisted private val hotelModel: HotelModel,
    private val router: Router,
) : MvpPresenter<HotelInfoView>() {



    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        setHotelName()
        setHotelType()
    }

    private fun setHotelName() {
        viewState.setHotelName(hotelModel.name)
    }

    private fun setHotelType() {
        viewState.setHotelType(hotelModel.type)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}

@AssistedFactory
interface HotelInfoPresenterFactory{
    fun presenter(hotelModel: HotelModel): HotelInfoPresenter
}