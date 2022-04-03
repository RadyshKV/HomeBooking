package com.geekbrains.homebooking.ui.hotels.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.homebooking.databinding.ItemHotelBinding
import com.geekbrains.homebooking.ui.hotels.HotelItemView
import com.geekbrains.homebooking.ui.hotels.HotelsPresenter


class HotelsAdapter (
    private val presenter: HotelsPresenter.HotelsListPresenter
) : RecyclerView.Adapter<HotelsAdapter.HotelViewHolder>() {

    inner class HotelViewHolder(private val vb: ItemHotelBinding) : RecyclerView.ViewHolder(vb.root),
        HotelItemView {
        override fun setName(name: String) {
            vb.hotelsFragmentRecyclerItemTextView.text = name
        }

        override var pos: Int = -1
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