package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.remote.RetrofitService
import com.geekbrains.homebooking.db.cache.ICitiesCache
import com.geekbrains.homebooking.remote.connectivity.NetworkStatus
import com.geekbrains.homebooking.remote.model.getCityRequestBody
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class CitiesRepositoryImpl @Inject constructor(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val citiesCache: ICitiesCache,
) : CitiesRepository {

    override fun getCities(): Single<List<CityModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getCities(getCityRequestBody())
                .flatMap {citiesCache.setCities(it.response)}
        } else {
            citiesCache.getCities()
        }
    }
}