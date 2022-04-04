package com.geekbrains.homebooking.ui.cities.adapter

import com.geekbrains.homebooking.ui.imageloading.ImageLoader
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.homebooking.databinding.ItemCityBinding
import com.geekbrains.homebooking.ui.cities.CitiesPresenter
import com.geekbrains.homebooking.ui.cities.CityItemView


class CitiesAdapter(
    private val presenter: CitiesPresenter.CitiesListRVPresenter,
    //private val imageLoader: ImageLoader<ImageView>
) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    inner class CityViewHolder(private val vb: ItemCityBinding) : RecyclerView.ViewHolder(vb.root),
        CityItemView {
        override fun setName(name: String) {
            vb.citiesFragmentRecyclerItemTextView.text = name
        }

        override fun loadImage(id: Int) {
            vb.imageCity.setImageResource(id)
            //imageLoader.loadInto(imageUrl, vb.imageCity)
        }

        override var pos: Int = -1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder(
            ItemCityBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }
}