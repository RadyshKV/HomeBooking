package com.geekbrains.homebooking.ui.account.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.homebooking.databinding.ItemBookingBinding
import com.geekbrains.homebooking.ui.account.AccountPresenter
import com.geekbrains.homebooking.ui.account.BookingItemView


class BookingAdapter(
    private val presenter: AccountPresenter.BookingsListPresenter,

    ) : RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    inner class BookingViewHolder(private val vb: ItemBookingBinding) :
        RecyclerView.ViewHolder(vb.root),
        BookingItemView {

        override fun setListener() {
            vb.submitButton.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }


        override fun setRoomName(roomName: String?) {
            vb.roomName.text = roomName
        }

        override fun setAccName(accName: String?) {
            vb.accName.text = accName
        }

        override fun setMealName(mealName: String?) {
            vb.mealName.text = mealName
        }

        override fun setDateBegin(dateBegin: String?) {
            vb.dateBegin.text = dateBegin
        }

        override fun setDateEnd(dateEnd: String?) {
            vb.dateEnd.text = dateEnd
        }

        override fun setNights(nights: Int?) {
            vb.nights.text = nights.toString()
        }

        override fun setPrice(price: Int?) {
            vb.price.text = price.toString()
        }

        override fun setCurrency(currency: String?) {
            vb.currency.text = currency
        }

        override var pos: Int = -1

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        return BookingViewHolder(
            ItemBookingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        presenter.bindView(holder.apply {
            pos = position
        })
    }

    override fun getItemCount(): Int {
        return presenter.getCount()
    }
}