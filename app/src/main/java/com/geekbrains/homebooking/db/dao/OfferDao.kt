package com.geekbrains.homebooking.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekbrains.homebooking.db.model.RoomOffer

@Dao
interface OfferDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(offer: RoomOffer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(offers: List<RoomOffer>)

    @Query("SELECT * FROM RoomOffer")
    fun getAll(): List<RoomOffer>

    @Query("SELECT * FROM RoomOffer WHERE hotelId = :hotelId")
    fun getByHotelId(hotelId: Int?): List<RoomOffer>

    @Query("SELECT * FROM RoomOffer WHERE cityId = :cityId")
    fun getByCityId(cityId: Int?): List<RoomOffer>

}