package com.geekbrains.homebooking.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class UserModel (
    @Expose
    var id: Int?=0,

    @Expose
    var email: String? = "",

    @Expose
    var password: String? = "",

    @Expose
    var first_name: String? = "",

    @Expose
    var last_name: String? = "",

    @Expose
    var middle_name: String? = "",

    @Expose
    var birthday: String? = "",

    @Expose
    var passport_number: String? = "",

    @Expose
    var passport_date: String? = "",

    @Expose
    var phone: String? = "",

    @Expose
    var sex: String? = "",

    @Expose
    var citizen: String? = "",

    @Expose
    var date_joined: String? = "",

    ) : Serializable

