package com.geekbrains.homebooking.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class UserModel (
    @Expose
    val id: Int?,

    @Expose
    val email: String?,

    @Expose
    val password: String?,

    @Expose
    val first_name: String?,

    @Expose
    val last_name: String?,

    @Expose
    val middle_name: String?,

    @Expose
    val birthday: String?,

    @Expose
    val passport_number: String?,

    @Expose
    val passport_date: String?,

    @Expose
    val phone: String?,

    @Expose
    val sex: String?,

    @Expose
    val citizen: String?,

    @Expose
    val date_joined: String?,
    @Expose
    val token: String?,

    ) : Serializable