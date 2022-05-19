package com.geekbrains.homebooking.ui.auth

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.Skip

interface LoginView: MvpView {

    @AddToEndSingle
    fun setEmail(email: String)

    @AddToEndSingle
    fun setPassword(password: String)

    @Skip
    fun showMessage()

    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()
}
