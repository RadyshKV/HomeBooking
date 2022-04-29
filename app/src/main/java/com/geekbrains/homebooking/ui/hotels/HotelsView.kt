package com.geekbrains.homebooking.ui.hotels

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import java.util.*

interface HotelsView : MvpView {
    @AddToEndSingle
    fun updateList()

    @AddToEndSingle
    fun setDateBegin(date: Calendar)

    @AddToEndSingle
    fun setDateEnd(date: Calendar)

    @AddToEndSingle
    fun setAdult(adult: Int)

    @AddToEndSingle
    fun showLoading()

    @AddToEndSingle
    fun hideLoading()

}