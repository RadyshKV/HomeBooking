package com.geekbrains.homebooking.ui.auth

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface LoginView: MvpView {

    @AddToEndSingle
    fun setEmail(email: String)

    @AddToEndSingle
    fun setPassword(password: String)

}
