package com.geekbrains.homebooking.ui.hotel_info

import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.model.OfferModel
import com.geekbrains.homebooking.ui.base.IListPresenter
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

class HotelInfoPresenter @AssistedInject constructor(
    @Assisted private val hotelModel: HotelModel,
    private val router: Router,
) : MvpPresenter<HotelInfoView>() {

    val offersListPresenter = OffersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initView()
        setOfferList()
    }

    private fun initView() {
        setHotelAddress()
        setHotelName()
        setHotelType()
    }

    private fun setOfferList() {
        offersListPresenter.offers.addAll(hotelModel.offers)
        viewState.updateList()
    }

    private fun setHotelName() {
        viewState.setHotelName(hotelModel.name)
    }

    private fun setHotelType() {
        viewState.setHotelType(hotelModel.type)
    }

    private fun setHotelAddress() {
        viewState.setHotelAddress(hotelModel.address)
    }

    class OffersListPresenter : IListPresenter<OfferItemView> {
        val offers = mutableListOf<OfferModel?>()

        override var itemClickListener: ((OfferItemView) -> Unit)? = {}

        override fun bindView(view: OfferItemView) {
            val offer = offers[view.pos]
            view.setAccName(offer?.acc_name)
            view.setRoomName(offer?.room_name)
            view.setTariffName(offer?.tariff_name)
            view.setDateBegin(offer?.date_begin)
            view.setDateEnd(offer?.date_end)
            view.setMealName(offer?.meal_name)
            view.setNights(offer?.nights)
            view.setQuote(offer?.quote)
        }
        override fun getCount() = offers.size
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