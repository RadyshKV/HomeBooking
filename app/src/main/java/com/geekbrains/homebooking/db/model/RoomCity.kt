package com.geekbrains.homebooking.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomCity(
    @PrimaryKey val id: String,
    val name: String,
    val imageUrl: String,
)
