package com.geekbrains.homebooking.ui.cities

import com.geekbrains.homebooking.ui.base.IListPresenter
import android.util.Log
import com.geekbrains.homebooking.domain.CitiesRepository
import com.geekbrains.homebooking.model.*
import com.geekbrains.homebooking.navigation.AppScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject

class CitiesPresenter @Inject constructor(
    private val router: Router,
    private val citiesRepository: CitiesRepository,
    private val appScreens: AppScreens,
) : MvpPresenter<CitiesView>() {

    val citiesListPresenter = CitiesListPresenter()
    val citiesListRVPresenter = CitiesListRVPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
        citiesListPresenter.itemClickListener = {
            router.navigateTo(
                appScreens.hotelsScreen(
                    citiesListPresenter.cities[it.pos]
                )
            )
        }

        citiesListRVPresenter.itemClickListener = {
            router.navigateTo(
                appScreens.hotelsScreen(
                   citiesListRVPresenter.cities[it.pos]
                )
            )
        }
    }

    private fun loadData() {

        citiesListRVPresenter.cities.addAll(getCities())

        citiesRepository.getCities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { cities ->
                    citiesListPresenter.cities.addAll(cities)
                    viewState.updateList()
                    viewState.hideLoading()
                }, { e ->
                    Log.e("Retrofit", "Ошибка при получении городов", e)
                    viewState.hideLoading()
                }
            )
    }

    class CitiesListPresenter : IListPresenter<CityItemView> {
        val cities = mutableListOf<CityModel>()

        override var itemClickListener: ((CityItemView) -> Unit)? = {}

        override fun bindView(view: CityItemView) {
            val city = cities[view.pos]
            view.setName(city.name)
            //view.loadImage(city.imageUrl)
        }

        override fun getCount() = cities.size
    }

    class CitiesListRVPresenter : IListPresenter<CityItemView> {
        val cities = mutableListOf<CityModel>()

        override var itemClickListener: ((CityItemView) -> Unit)? = {}

        override fun bindView(view: CityItemView) {
            view.setName(getCityName()[view.pos])
            view.loadImage(getCityImage()[view.pos])
        }

        override fun getCount() = cities.size
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}


