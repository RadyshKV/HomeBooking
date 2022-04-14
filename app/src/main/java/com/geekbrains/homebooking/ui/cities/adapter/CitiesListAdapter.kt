package com.geekbrains.homebooking.ui.cities.adapter

import android.content.Context
import android.widget.ArrayAdapter
import com.geekbrains.homebooking.ui.cities.CitiesPresenter
import com.geekbrains.homebooking.ui.cities.CityItemView


class CitiesListAdapter(
    context: Context,
    private val presenter: CitiesPresenter.CitiesListPresenter,
) : ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, presenter.getCitiesNameList()) {


}