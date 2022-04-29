package com.geekbrains.homebooking.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RoomHotel::class,
            parentColumns = ["id"],
            childColumns = ["hotelId"],
            onDelete = ForeignKey.CASCADE
        ),
//        ForeignKey(
//            entity = RoomCity::class,
//            parentColumns = ["id"],
//            childColumns = ["cityId"],
//            onDelete = ForeignKey.CASCADE
//        )
    ]
)
data class RoomOffer(

    @PrimaryKey val id: String,

    val hotelId: Int,

    val cityId: Int?,

    val price: Int?,

    val currency: String?,

    val accName: String?,

    val roomName: String?,

    val mealName: String?,

    val mealCode: String?,

    val tariffName: String?,

    val dateBegin: String?,

    val dateEnd: String?,

    val nights: Int?,

    val quote: Int?,
)