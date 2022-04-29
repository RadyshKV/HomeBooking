package com.geekbrains.homebooking.ui.hotel_info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.homebooking.databinding.ItemOfferBinding
import com.geekbrains.homebooking.ui.hotel_info.HotelInfoPresenter
import com.geekbrains.homebooking.ui.hotel_info.OfferItemView


class OffersRecyclerViewAdapter(
    private val presenter: HotelInfoPresenter.OffersListPresenter,
//private val imageLoader: ImageLoader<ImageView>

) : RecyclerView.Adapter<OffersRecyclerViewAdapter.OfferViewHolder>() {

    inner class OfferViewHolder(private val vb: ItemOfferBinding) :
        RecyclerView.ViewHolder(vb.root),
        OfferItemView {
        override fun setAccName(accName: String?) {
            vb.accName.text = accName
        }

        override fun setRoomName(roomName: String?) {
            vb.roomName.text = roomName
        }

        override fun setMealName(mealName: String?) {
            vb.mealName.text = mealName
        }

        override fun setTariffName(tariffName: String?) {
            vb.tariffName.text = tariffName
        }

        override fun setNights(nights: Int?) {
            vb.nights.text = nights.toString()
        }

        override fun setQuote(quote: Int?) {
            vb.quote.text = quote.toString()
        }

        override fun setDateEnd(dateEnd: String?) {
            vb.dateEnd.text = dateEnd
        }

        override fun setDateBegin(dateBegin: String?) {
            vb.dateBegin.text = dateBegin
        }

        override var pos: Int = -1

//        override fun loadImage(imageUrl: String?) {
//            imageLoader.loadInto(imageUrl, vb.imageHotel)
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

}