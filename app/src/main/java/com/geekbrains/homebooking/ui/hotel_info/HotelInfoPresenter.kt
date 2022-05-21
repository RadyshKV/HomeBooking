package com.geekbrains.homebooking.ui.hotel_info

import android.util.Log
import com.geekbrains.homebooking.domain.BookingRepository
import com.geekbrains.homebooking.model.AuthorizationState
import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.model.OfferModel
import com.geekbrains.homebooking.navigation.AppScreens
import com.geekbrains.homebooking.ui.hotel_info.adapter.Cell
import com.geekbrains.homebooking.ui.hotel_info.adapter.CellOffer
import com.geekbrains.homebooking.ui.hotel_info.adapter.CellRoom
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class HotelInfoPresenter @AssistedInject constructor(
    @Assisted private val hotelModel: HotelModel,
    private val router: Router,
    private val appScreens: AppScreens,
    private val bookingRepository: BookingRepository,
) : MvpPresenter<HotelInfoView>() {

    val offersListPresenter = OffersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initView()
        setOfferList()
        offersListPresenter.itemClickListener = { offer ->
            if (AuthorizationState.isAuthorized == true) {
                viewState.showDialog(offer)
            } else {
                router.navigateTo(
                    appScreens.loginScreen()
                )
            }
        }
    }


    fun booking(offer: OfferModel) {
        bookingRepository.postBooking(offer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                {
                    viewState.hideLoading()
                    viewState.showMessage()
                }, { e ->
                    Log.e("Retrofit", "Ошибка при бронировании отеля", e)
                    viewState.hideLoading()
                }
            )
    }

    private fun initView() {
        setHotelAddress()
        setHotelImage()
    }

    private fun setHotelImage() {
        viewState.loadImage(hotelModel.images.firstOrNull())
    }

    private fun setOfferList() {
        offersListPresenter.offers.addAll(hotelModel.offers)
        offersListPresenter.addItems()
        viewState.updateList()
    }

    private fun setHotelAddress() {
        viewState.setHotelAddress(hotelModel.address)
    }

    class OffersListPresenter {
        var items: ArrayList<Cell> = ArrayList()
        val offers = mutableListOf<OfferModel?>()
        var itemClickListener: ((OfferModel) -> Unit)? = {}
        fun getCount() = items.size

        fun addItems() {
            val rooms = mutableListOf<String?>()
            offers.forEach {
                rooms.add(it?.room_name)
            }
            val listRooms = rooms.distinct()
            listRooms.forEach { roomName ->
                items.add(CellRoom(roomName.toString()))
                offers.forEach { offer ->
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