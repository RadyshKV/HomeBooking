package com.geekbrains.homebooking.ui.hotels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.homebooking.databinding.ItemHotelBinding
import com.geekbrains.homebooking.ui.hotels.HotelItemView
import com.geekbrains.homebooking.ui.hotels.HotelsPresenter
import com.geekbrains.homebooking.ui.imageloading.ImageLoader


class HotelsAdapter (
    private val presenter: HotelsPresenter.HotelsListPresenter,
    private val imageLoader: ImageLoader<ImageView>

) : RecyclerView.Adapter<HotelsAdapter.HotelViewHolder>() {

    inner class HotelViewHolder(private val vb: ItemHotelBinding) : RecyclerView.ViewHolder(vb.root),
        HotelItemView {
        override fun setName(name: String) {
            vb.hotelsName.text = name
        }

        override fun setType(type: String) {
            vb.hotelsType.text = type
        }



        override var pos: Int = -1

        override fun loadImage(imageUrl: String?) {
            imageLoader.loadInto(imageUrl, vb.imageHotel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        return HotelViewHolder(
            ItemHotelBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }
}