package com.geekbrains.homebooking.ui.hotel_info.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.homebooking.databinding.ItemOfferBinding
import com.geekbrains.homebooking.databinding.ItemRoomBinding
import com.geekbrains.homebooking.model.OfferModel
import com.geekbrains.homebooking.ui.hotel_info.HotelInfoPresenter


class OffersRecyclerViewAdapter(
    private val presenter: HotelInfoPresenter.OffersListPresenter,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class RoomViewHolder(private val vb: ItemRoomBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(cell: CellRoom) {
            vb.roomName.text = cell.roomName
        }
    }

    inner class OfferViewHolder(private val vb: ItemOfferBinding) :
        RecyclerView.ViewHolder(vb.root) {

        fun bind(cell: CellOffer) {
            vb.accName.text = cell.offer?.acc_name
            vb.mealName.text = cell.offer?.meal_name
            vb.dateBegin.text = cell.offer?.date_begin
            vb.dateEnd.text = cell.offer?.date_end
            vb.nights.text = cell.offer?.nights.toString()
            vb.price.text = cell.offer?.price.toString()
            vb.currency.text = cell.offer?.currency.toString()
            vb.submitButton.setOnClickListener {
                cell.offer?.let { it ->
                    presenter.itemClickListener?.invoke(it)
                }
            }
        }
    }

    override fun getItemViewType(position: Int) = presenter.items[position].identifier()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CellOffer::class.java.name.hashCode() ->
                OfferViewHolder(
                    ItemOfferBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            CellRoom::class.java.name.hashCode() ->
                RoomViewHolder(
                    ItemRoomBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else -> throw IllegalArgumentException("Unsupported layout") // in case populated with a model we don't know how to display.
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = presenter.items[holder.adapterPosition]) {
            is CellOffer -> (holder as OfferViewHolder).bind(item)
            is CellRoom -> (holder as RoomViewHolder).bind(item)
        }
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }

}

open class Cell {
    fun identifier() = this::class.java.name.hashCode()
}

class CellOffer(
    val offer: OfferModel?
) : Cell()

class CellRoom(
    val roomName: String
) : Cell()
