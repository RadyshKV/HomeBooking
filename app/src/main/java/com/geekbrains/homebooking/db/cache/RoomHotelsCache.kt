package com.geekbrains.homebooking.db.cache

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.db.AppDatabase
import com.geekbrains.homebooking.db.model.RoomHotel
import com.geekbrains.homebooking.model.Geo
import io.reactivex.rxjava3.core.Single

class RoomHotelsCache(
    private val db: AppDatabase
) : IHotelsCache {

    override fun setHotels(hotels: List<HotelModel>): Single<List<HotelModel>> {
        return Single.fromCallable {
            val roomHotels = hotels.map { hotel ->
                RoomHotel(
                    hotel.id, hotel.name, hotel.type, hotel.url, hotel.address,
                    hotel.geo.country_id.firstOrNull(), hotel.geo.region_id.firstOrNull(), hotel.geo.resort_id.firstOrNull(),
                    hotel.geo.city_id.firstOrNull(), hotel.geo.lat, hotel.geo.lng,
                    hotel.images.firstOrNull()
                )
            }
                db.hotelDao.insert(roomHotels)
            hotels
        }
    }

    override fun getHotels(cityModel: CityModel): Single<List<HotelModel>> {
        return Single.fromCallable {
            db.hotelDao.getByCityId(cityModel.id).map { roomModel ->
                HotelModel(
                    roomModel.id, roomModel.name, roomModel.type, roomModel.url, roomModel.address,
                    Geo(
                        listOf(roomModel.countryId), listOf(roomModel.regionId), listOf(roomModel.resortId),
                        listOf(roomModel.cityId), roomModel.lat, roomModel.lng
                    ),
                    listOf(roomModel.image)
                )
            }
        }
    }
}