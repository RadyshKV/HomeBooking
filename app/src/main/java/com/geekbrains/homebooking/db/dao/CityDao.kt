package com.geekbrains.homebooking.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekbrains.homebooking.db.model.RoomCity

@Dao
interface CityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: RoomCity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cities: List<RoomCity>)

    @Query( "SELECT * FROM RoomCity" )
    fun getAll(): List<RoomCity>

    @Query( "SELECT * FROM RoomCity WHERE id = :id LIMIT 1" )
    fun getById(id: String): RoomCity?
}