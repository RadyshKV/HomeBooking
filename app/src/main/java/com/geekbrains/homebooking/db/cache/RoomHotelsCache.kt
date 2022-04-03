package com.geekbrains.homebooking.db.cache

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.model.HotelModel
import com.geekbrains.homebooking.db.AppDatabase
import com.geekbrains.homebooking.db.model.RoomHotel
import io.reactivex.rxjava3.core.Single

class RoomHotelsCache(
    private val db: AppDatabase
) : IHotelsCache {

    override fun setHotels(hotels: List<HotelModel>): Single<List<HotelModel>> {
        return Single.fromCallable {
            val roomHotels = hotels.map { hotel ->
                RoomHotel(hotel.id, hotel.name, hotel.cityId, hotel.address, hotel.website,
                    hotel.email, hotel.telephone, hotel.coord_w, hotel.coord_i, hotel.rating)
            }
            db.hotelDao.insert(roomHotels)
            hotels
        }
    }

    override fun getHotels(cityModel: CityModel): Single<List<HotelModel>> {
        return Single.fromCallable {
            db.hotelDao.getByCityId(cityModel.id).map { roomModel ->
                HotelModel(
                    roomModel.id, roomModel.name, roomModel.cityId, roomModel.address, roomModel.website,
                    roomModel.email, roomModel.telephone, roomModel.coord_w, roomModel.coord_i,
                     roomModel.rating
                )
            }
        }
    }
}