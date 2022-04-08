package com.geekbrains.homebooking.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekbrains.homebooking.db.model.RoomHotel

@Dao
interface HotelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hotel: RoomHotel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(hotels: List<RoomHotel>)

    @Query("SELECT * FROM RoomHotel")
    fun getAll(): List<RoomHotel>

    @Query("SELECT * FROM RoomHotel WHERE cityId = :cityId")
    fun getByCityId(cityId: Int?): List<RoomHotel>
}