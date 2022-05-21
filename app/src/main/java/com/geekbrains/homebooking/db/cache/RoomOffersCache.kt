package com.geekbrains.homebooking.db.cache

import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.db.AppDatabase
import com.geekbrains.homebooking.db.model.RoomOffer
import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.OfferModel
import io.reactivex.rxjava3.core.Single

class RoomOffersCache(
    private val db: AppDatabase
) : IOffersCache {

    override fun setOffers(offers: List<OfferModel>): Single<List<OfferModel>> {
        return Single.fromCallable {
            val roomOffers = offers.map { offer ->
                RoomOffer(
                    offer.id,
                    offer.hotel_id,
                    offer.city_id,
                    offer.price,
                    offer.currency,
                    offer.acc_name,
                    offer.room_name,
                    offer.meal_name,
                    offer.meal_code,
                    offer.tariff_name,
                    offer.date_begin,
                    offer.date_end,
                    offer.nights,
                    offer.quote
                )
            }
            db.offerDao.insert(roomOffers)
            offers
        }
    }

    override fun getOffers(hotelModel: HotelModel): Single<List<OfferModel>> {
        return Single.fromCallable {
            db.offerDao.getByHotelId(hotelModel.id).map { roomModel ->
                OfferModel(
                    roomModel.id,
                    "",
                    roomModel.hotelId,
                    roomModel.cityId,
                    roomModel.price,
                    roomModel.currency,
                    roomModel.accName,
                    roomModel.roomName,
                    roomModel.mealName,
                    roomModel.mealCode,
                    roomModel.tariffName,
                    roomModel.dateBegin,
                    roomModel.dateEnd,
                    roomModel.nights,
                    roomModel.quote
                )
            }
        }
    }

    override fun getOffers(cityModel: CityModel): Single<List<OfferModel>> {
        return Single.fromCallable {
            db.offerDao.getByCityId(cityModel.id).map { roomModel ->
                OfferModel(
                    roomModel.id,
                    "",
                    roomModel.hotelId,
                    roomModel.cityId,
                    roomModel.price,
                    roomModel.currency,
                    roomModel.accName,
                    roomModel.roomName,
                    roomModel.mealName,
                    roomModel.mealCode,
                    roomModel.tariffName,
                    roomModel.dateBegin,
                    roomModel.dateEnd,
                    roomModel.nights,
                    roomModel.quote
                )
            }
        }
    }
}