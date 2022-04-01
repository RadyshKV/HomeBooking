package com.geekbrains.homebooking.ui.cities

import OnItemViewCityClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.homebooking.databinding.ItemCityBinding
import com.geekbrains.homebooking.model.City

class CitiesFragmentAdapter(private val itemCityClickListener: OnItemViewCityClickListener)
    : RecyclerView.Adapter<CitiesFragmentAdapter.MainViewHolder>() {
    private var cityData: List<City> = listOf()
    private lateinit var binding: ItemCityBinding

    fun setCity(data: List<City>) {
        cityData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MainViewHolder {
        binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(cityData[position])
    }

    override fun getItemCount(): Int {
        return cityData.size
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(city: City) = with(binding) {
            citiesFragmentRecyclerItemTextView.text = city.city
            imageCity.setImageResource(city.id)

            root.setOnClickListener {
                itemCityClickListener.onItemViewClick(city)
            }
        }
    }
}