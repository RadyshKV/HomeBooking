package com.geekbrains.homebooking.ui.hotels

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface HotelsView : MvpView {
    @AddToEndSingle
    fun updateList()

    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()

}