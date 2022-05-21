package com.geekbrains.homebooking.ui.account

import com.geekbrains.homebooking.model.UserModel
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface AccountView: MvpView {


    @AddToEndSingle
    fun updateList()

    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()

    @AddToEndSingle
    fun updateUserData(userModel: UserModel)

}
