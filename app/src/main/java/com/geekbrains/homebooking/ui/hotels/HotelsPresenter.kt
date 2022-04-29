package com.geekbrains.homebooking.ui.hotels

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.ui.base.IListPresenter
import android.util.Log
import com.geekbrains.homebooking.domain.HotelsRepository
import com.geekbrains.homebooking.domain.OffersRepository
import com.geekbrains.homebooking.model.OfferModel
import com.geekbrains.homebooking.navigation.AppScreens
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HotelsPresenter @AssistedInject constructor(
    private val router: Router,
    private val hotelsRepository: HotelsRepository,
    private val offersRepository: OffersRepository,
    private val appScreens: AppScreens,
    @Assisted("cityModel") private val cityModel: CityModel,
) : MvpPresenter<HotelsView>() {

    val hotelsListPresenter = HotelsListPresenter()
    private val allHotels = mutableListOf<HotelModel>()
    private var dateBegin = Calendar.getInstance()
    private var dateEnd = Calendar.getInstance()
    private val adults = listOf(1, 2, 3, 4)
    private var adultPosition = 0
    private val dateAPIFormat = "yyyy-MM-dd"
    private val simpleDateAPIFormat = SimpleDateFormat(dateAPIFormat, Locale.getDefault())

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        initView()
        loadData()
        hotelsListPresenter.itemClickListener = {
            router.navigateTo(
                appScreens.hotelInfoScreen(
                    hotelsListPresenter.hotels[it.pos]
                )
            )
        }
    }

    private fun initView() {
        dateEnd[Calendar.DAY_OF_MONTH]++
        viewState.setDateBegin(dateBegin)
        viewState.setDateEnd(dateEnd)
        viewState.setAdult(adultPosition)
    }

    private fun loadData() {
        hotelsRepository.getHotels(cityModel, 1000, 0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { hotels ->
                    allHotels.clear()
                    allHotels.addAll(hotels)
                    offersRepository.getOffers(cityModel, simpleDateAPIFormat.format(dateBegin.time), simpleDateAPIFormat.format(dateEnd.time), adults[adultPosition])
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { viewState.showLoading() }
                        .subscribe(
                            { offers ->
                                if (offers.isNotEmpty())
                                    offerIntoHotel(hotels, offers).filter { it.offers.isNotEmpty() }
                                        .let {
                                            hotelsListPresenter.hotels.clear()
                                            hotelsListPresenter.hotels.addAll(it)
                                        }
                                viewState.updateList()
                                viewState.hideLoading()
                            }, { e ->
                                Log.e("Retrofit", "Ошибка при получении офферов", e)
                                viewState.hideLoading()
                            }
                        )

                }, { e ->
                    Log.e("Retrofit", "Ошибка при получении отелей", e)
                    viewState.hideLoading()
                }
            )
    }

    fun reLoadData() {

        offersRepository.getOffers(cityModel, simpleDateAPIFormat.format(dateBegin.time), simpleDateAPIFormat.format(dateEnd.time), adults[adultPosition])
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { offers ->
                    if (offers.isNotEmpty())
                        offerIntoHotel(allHotels, offers).filter { it.offers.isNotEmpty() }
                            .let {
                                hotelsListPresenter.hotels.clear()
                                hotelsListPresenter.hotels.addAll(it)
                            }
                    viewState.updateList()
                    viewState.hideLoading()
                }, { e ->
                    Log.e("Retrofit", "Ошибка при получении офферов", e)
                    viewState.hideLoading()
                }
            )

    }

    private fun offerIntoHotel(
        hotels: List<HotelModel>,
        offers: List<OfferModel>
    ): List<HotelModel>{
        val h = mutableListOf<HotelModel>()
            h.addAll(hotels)
         h.forEach { hotel ->
             val o = mutableListOf<OfferModel>()
            offers.forEach { offer ->
                if (offer.hotel_id == hotel.id)
                    o.add(offer)
            }
             hotel.offers = o.toMutableList()
        }
        return h
    }

    class HotelsListPresenter : IListPresenter<HotelItemView> {
        val hotels = mutableListOf<HotelModel>()

        override var itemClickListener: ((HotelItemView) -> Unit)? = {}

        override fun bindView(view: HotelItemView) {
            val hotel = hotels[view.pos]
            view.setName(hotel.name)
            view.setType(hotel.type)
            view.loadImage(hotel.images.firstOrNull())
            view.setOffers(hotel.offers.count())
            view.setMinCost(getMinCost(hotel))
        }

        private fun getMinCost(hotel: HotelModel): Int {
            val prices = arrayListOf<Int>()
            hotel.offers.forEach{ offer ->
                offer?.price?.let {  prices.add(it) }
            }
            return prices.minOf { it }
        }


        override fun getCount() = hotels.size
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun setdateBegin(dateBegin: Calendar) {
        this.dateBegin.time = dateBegin.time
    }

    fun setDateEnd(dateEnd: Calendar) {
        this.dateEnd.time = dateEnd.time
    }

    fun setAdult(selectedItemPosition: Int) {
        adultPosition = selectedItemPosition
    }
}

@AssistedFactory
interface HotelsPresenterFactory {
    fun presenter(
        @Assisted("cityModel") cityModel: CityModel
    ): HotelsPresenter
}
