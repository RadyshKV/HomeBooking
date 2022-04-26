package com.geekbrains.homebooking.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RoomCity::class,
            parentColumns = ["id"],
            childColumns = ["cityId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RoomHotel(

    @PrimaryKey val id: Int,

    val name: String,

    val type: String?,

    val url: String?,

    val address: String?,

    val countryId: Int?,

    val regionId: Int?,

    val resortId: Int?,

    val cityId: Int?,

    val lat: String?,

    val lng: String?,

   // val desc: String,

    val image: String?,
)