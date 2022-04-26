package com.geekbrains.homebooking.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.db.dao.HotelDao
import com.geekbrains.homebooking.db.dao.CityDao
import com.geekbrains.homebooking.db.dao.OfferDao
import com.geekbrains.homebooking.db.model.RoomCity
import com.geekbrains.homebooking.db.model.RoomHotel
import com.geekbrains.homebooking.db.model.RoomOffer

@Database(
    entities = [
        RoomCity::class,
        RoomHotel::class,
        RoomOffer::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract val cityDao: CityDao
    abstract val hotelDao: HotelDao
    abstract val offerDao: OfferDao

    companion object{
        private const val DB_NAME = "database.db"
        val instance by lazy {
            Room.databaseBuilder(App.instance, AppDatabase::class.java, DB_NAME)
                .build()
        }
    }
}