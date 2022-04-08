package com.geekbrains.homebooking.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCity(
    @PrimaryKey val id: Int?,

    val name: String?,

    val resort_id: Int?,

    val resort_name: String?,

    val region_id: Int?,

    val region_name: String?,
)
