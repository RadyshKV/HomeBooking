package com.geekbrains.homebooking.ui.hotel_info

import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.model.OfferModel
import com.geekbrains.homebooking.ui.base.IListPresenter
import com.geekbrains.homebooking.ui.hotel_info.adapter.Cell
import com.geekbrains.homebooking.ui.hotel_info.adapter.CellOffer
import com.geekbrains.homebooking.ui.hotel_info.adapter.CellRoom
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
        offersListPresenter.addItems()
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

    class OffersListPresenter {
        var items: ArrayList<Cell> = ArrayList()
        val offers = mutableListOf<OfferModel?>()

        fun getCount() = items.size

        fun addItems() {
            val rooms = mutableListOf<String?>()
            offers.forEach {
                rooms.add(it?.room_name)
            }
            val listRooms = rooms.distinct()
            listRooms.forEach{ roomName->
                items.add(CellRoom(roomName.toString()))
                offers.forEach { offer->
                    if (offer?.room_name.equals(roomName))
                        items.add(CellOffer(offer))
                }
            }
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}

@AssistedFactory
interface HotelInfoPresenterFactory {
    fun presenter(hotelModel: HotelModel): HotelInfoPresenter
}