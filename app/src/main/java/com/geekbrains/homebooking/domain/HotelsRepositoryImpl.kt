package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.remote.RetrofitService
import com.geekbrains.homebooking.db.cache.IHotelsCache
import com.geekbrains.homebooking.remote.connectivity.NetworkStatus
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class HotelsRepositoryImpl @Inject constructor(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val hotelsCache: IHotelsCache,
) : HotelsRepository {

    override fun getHotels(cityModel: CityModel): Single<List<HotelModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getHotels()
                .flatMap { hotels -> hotelsCache.setHotels(hotels)}
        } else {
            hotelsCache.getHotels(cityModel)
        }
    }
}