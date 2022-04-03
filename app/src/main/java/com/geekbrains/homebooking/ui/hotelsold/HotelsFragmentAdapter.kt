package com.geekbrains.homebooking.ui.hotelsold

import OnItemViewHotelClickListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.homebooking.databinding.ItemHotelBinding
import com.geekbrains.homebooking.model.Hotel


class HotelsFragmentAdapter(private val itemHotelClickListener: OnItemViewHotelClickListener)
    : RecyclerView.Adapter<HotelsFragmentAdapter.MainViewHolder>() {
    private var hotelData: List<Hotel> = listOf()
    private lateinit var binding: ItemHotelBinding

    fun setHotel(data: List<Hotel>) {
        hotelData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): MainViewHolder {
        binding = ItemHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(hotelData[position])
    }

    override fun getItemCount(): Int {
        return hotelData.size
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(hotel: Hotel) = with(binding) {
            hotelsFragmentRecyclerItemTextView.text = hotel.hotel
            imageHotel.setImageResource(hotel.id)

            root.setOnClickListener {
                itemHotelClickListener.onItemViewClick(hotel)
            }
        }
    }
}