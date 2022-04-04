package com.geekbrains.homebooking.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose


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
    @PrimaryKey val id: String,
    val name: String,
    val cityId: String,
    val address: String,
    val website: String,
    val email: String,
    val telephone: String,
    val coord_w: String,
    val coord_i: String,
    val rating: Int,
)
