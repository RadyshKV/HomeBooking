package com.geekbrains.homebooking.db.cache

import com.geekbrains.homebooking.model.CityModel
import com.geekbrains.homebooking.db.AppDatabase
import com.geekbrains.homebooking.db.model.RoomCity
import io.reactivex.rxjava3.core.Single

class RoomCitiesCache(
    private val db: AppDatabase
): ICitiesCache {

    override fun setCities(cities: List<CityModel>): Single<List<CityModel>> {
        return Single.fromCallable {
            val roomCities = cities.map { city ->
                RoomCity(city.id, city.name ,city.region_id, city.region_name, city.resort_id, city.resort_name)
            }
            //db.cityDao.insert(roomCities)
            cities
        }
    }

    override fun getCities(): Single<List<CityModel>> {
        return Single.fromCallable {
            db.cityDao.getAll().map { roomModel ->
                CityModel(roomModel.id, roomModel.name ,roomModel.region_id, roomModel.region_name, roomModel.resort_id, roomModel.resort_name)
            }
        }
    }

}