package com.geekbrains.homebooking.ui.hotels

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import IListPresenter
import android.util.Log
import com.geekbrains.homebooking.domain.HotelsRepository
import com.geekbrains.homebooking.navigation.AppScreens
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter

class HotelsPresenter @AssistedInject constructor(
    private val router: Router,
    private val hotelsRepository: HotelsRepository,
    private val appScreens: AppScreens,
    @Assisted private val cityModel: CityModel
) : MvpPresenter<HotelsView>() {

    val hotelsListPresenter = HotelsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
        hotelsListPresenter.itemClickListener = {
            router.navigateTo(
                appScreens.hotelInfoScreen(
                    hotelsListPresenter.hotels.get(it.pos)
                )
            )
        }
    }

    private fun loadData() {

        hotelsRepository.getHotels(cityModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { hotels ->
                    hotelsListPresenter.hotels.addAll(hotels)
                    viewState.updateList()
                    viewState.hideLoading()
                }, { e ->
                    Log.e("Retrofit", "Ошибка при получении отелей", e)
                    viewState.hideLoading()
                }
            )
    }

    class HotelsListPresenter : IListPresenter<HotelItemView> {
        val hotels = mutableListOf<HotelModel>()

        override var itemClickListener: ((HotelItemView) -> Unit)? = {}

        override fun bindView(view: HotelItemView) {
            val hotel = hotels[view.pos]
            view.setName(hotel.name)

        }

        override fun getCount() = hotels.size
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}

@AssistedFactory
interface HotelsPresenterFactory{
    fun presenter(cityModel: CityModel): HotelsPresenter
}
