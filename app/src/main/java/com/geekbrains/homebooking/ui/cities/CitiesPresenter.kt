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
    val citiesRecyclerViewPresenter = CitiesRecyclerViewPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        Log.d("CitiesPresenter", "onFirstViewAttach")
        loadData()
        citiesListPresenter.itemClickListener = {cityName ->
            router.navigateTo(
                appScreens.hotelsScreen(
                    citiesListPresenter.cities.find{it.name.equals(cityName)}
                )
            )
        }

        citiesRecyclerViewPresenter.itemClickListener = {
            router.navigateTo(
                appScreens.hotelsScreen(
                   citiesRecyclerViewPresenter.popularCities[it.pos]
                )
            )
        }
    }

    private fun loadData() {

        citiesRecyclerViewPresenter.popularCities.addAll(getCities())

        citiesRepository.getCities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { cities ->
                    citiesListPresenter.cities.addAll(cities)
                    viewState.updateList()
                    viewState.hideLoading()
                    Log.d("Retrofit", "все ok")
                }, { e ->
                    Log.e("Retrofit", "Ошибка при получении городов", e)
                    viewState.hideLoading()
                }
            )
    }

    class CitiesListPresenter {

        val cities = mutableListOf<CityModel>()

        fun getCitiesNameList(): List<String?> {
           return cities.map { it.name }
        }

        var itemClickListener: ((String) -> Unit)? = {}

    }

    class CitiesRecyclerViewPresenter : IListPresenter<CityItemView> {
        val popularCities = mutableListOf<CityModel>()

         override var itemClickListener: ((CityItemView) -> Unit)? = {}

        override fun bindView(view: CityItemView) {
            view.setName(getCityName()[view.pos])
            view.loadImage(getCityImage()[view.pos])
        }

        override fun getCount() = popularCities.size
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}


