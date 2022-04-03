package com.geekbrains.homebooking.ui.cities
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface CitiesView: MvpView {
    @AddToEndSingle
    fun updateList()

    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()

}