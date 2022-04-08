package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.remote.RetrofitService
import com.geekbrains.homebooking.db.cache.IHotelsCache
import com.geekbrains.homebooking.remote.connectivity.NetworkStatus
import com.geekbrains.homebooking.remote.model.getHotelRequestBody
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class HotelsRepositoryImpl @Inject constructor(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val hotelsCache: IHotelsCache,
) : HotelsRepository {

    override fun getHotels(cityModel: CityModel, limit: Int, offset: Int): Single<List<HotelModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getHotels(getHotelRequestBody(cityModel.region_id, cityModel.resort_id, cityModel.id, limit, offset))
                .flatMap { hotelsCache.setHotels(it.response)}
        } else {
            hotelsCache.getHotels(cityModel)
        }
    }
}