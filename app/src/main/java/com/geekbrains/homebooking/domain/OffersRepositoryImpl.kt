package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.remote.RetrofitService
import com.geekbrains.homebooking.db.cache.IOffersCache
import com.geekbrains.homebooking.model.OfferModel
import com.geekbrains.homebooking.remote.connectivity.NetworkStatus
import com.geekbrains.homebooking.remote.model.getOfferRequestBody
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


class OffersRepositoryImpl @Inject constructor(
    private val networkStatus: NetworkStatus,
    private val retrofitService: RetrofitService,
    private val offersCache: IOffersCache,
) : OffersRepository {

    override fun getOffers(
        cityModel: CityModel,
        dateBegin: String,
        dateEnd: String,
        adult: Int
    ): Single<List<OfferModel>> {
        return if (networkStatus.isOnline()) {
            retrofitService.getOffers(
                getOfferRequestBody(
                    cityModel.region_id,
                    cityModel.resort_id,
                    cityModel.id,
                    null,
                    dateBegin,
                    dateEnd,
                    adult
                )
            )
                .flatMap { offersCache.setOffers(it.response) }
        } else {
            offersCache.getOffers(cityModel)
        }
    }
}