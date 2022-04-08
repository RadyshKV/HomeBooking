package com.geekbrains.homebooking.remote.model

import com.google.gson.annotations.Expose
import java.io.Serializable

data class APIHeader(
    @Expose
    val token: String,

    @Expose
    val method: String,
): Serializable