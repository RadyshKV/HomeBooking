package com.geekbrains.homebooking.model

import android.content.Context
import android.content.SharedPreferences
import com.geekbrains.homebooking.App
import com.google.gson.annotations.Expose
import java.io.Serializable

data class UserToken(

    @Expose
    var name: String? = "",

    @Expose
    var token: String? = "",

    ) : Serializable

object AuthorizationState {

    var isAuthorized: Boolean? = false

    var token: String? = ""

    fun writePref(){
        val mSettings: SharedPreferences = App.instance.getSharedPreferences("my_storage", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = mSettings.edit()
        isAuthorized?.let { editor.putBoolean("is_authorized", it).apply() }
        token?.let{ editor.putString("token", it).apply()}
    }

    fun readPref(){
        val mSettings: SharedPreferences = App.instance.getSharedPreferences("my_storage", Context.MODE_PRIVATE)
        isAuthorized = mSettings.getBoolean("is_authorized", false)
        token = mSettings.getString("token", "")
    }
}