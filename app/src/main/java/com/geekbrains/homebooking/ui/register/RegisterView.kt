package com.geekbrains.homebooking.ui.register

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip
import java.util.*

interface RegisterView: MvpView {

    @AddToEndSingle
    fun setEmail(email: String)

    @AddToEndSingle
    fun setPassword(password: String)

    @AddToEndSingle
    fun setConfirmPassword(password: String)

    @AddToEndSingle
    fun setFirstName(firstName: String)

    @AddToEndSingle
    fun setLastName(lastName: String)

    @AddToEndSingle
    fun setMiddleName(middleName: String)

    @AddToEndSingle
    fun setBirthday(date: Calendar)

    @AddToEndSingle
    fun setPassportNumber(passportNumber: String)

    @AddToEndSingle
    fun setPassportDate(date: Calendar)

    @AddToEndSingle
    fun setPhone(phone: String)

    @AddToEndSingle
    fun setSex(sex: String)

    @AddToEndSingle
    fun setCitizen(citizen: String)

    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()

    @Skip
    fun showEmptyMessage()

    @Skip
    fun showPasswordMessage()

}