package com.geekbrains.homebooking.ui.hotels.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.geekbrains.homebooking.R
import com.geekbrains.homebooking.databinding.SpinnerDropDownLayoutBinding
import com.geekbrains.homebooking.ui.hotels.HotelsPresenter


class GuestsAdapter(val context: Context, private var dataSource: List<Guest>) : BaseAdapter() {

    private var _binding: SpinnerDropDownLayoutBinding? = null
    private val binding
        get() = _binding!!

    //private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            _binding = SpinnerDropDownLayoutBinding.inflate(
                LayoutInflater.from(parent?.context),
                parent,
                false
            )
            view = binding.root
            //view = inflater.inflate(R.layout.custom_spinner_item, parent, false)
            vh = ItemHolder(binding)
            view.tag = vh
            //view.setOnClickListener { presenter.setAdult(position) }
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }
        vh.label.text = dataSource[position].name

        //val id = context.resources.getIdentifier(dataSource.get(position).url, "drawable", context.packageName)
        vh.img.setBackgroundResource(dataSource[position].image)
        return view
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private class ItemHolder(vb: SpinnerDropDownLayoutBinding) {
        val label: TextView = vb.nameTextView
        val img: ImageView = vb.imageIcon


    }

}


//class GuestsAdapter(context: Context, resource: Int, guests: List<Guest>) :
//    ArrayAdapter<Guest>(context, resource, guests) {
//
//
//    fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        val rowView: View = layoutInflater.inflate(R.layout.custom_spinner_adapter, null, true)
//        val guest: Guest? = getItem(position)
//        val textView = rowView.findViewById(R.id.nameTextView) as TextView
//        val imageView: ImageView = rowView.findViewById(R.id.imageIcon) as ImageView
//        textView.setText(guest.getName())
//        imageView.setImageResource(guest.getImage())
//        return rowView
//    }
//
//    override fun getDropDownView(
//        position: Int,
//        @Nullable convertView: View?,
//        @NonNull parent: ViewGroup?
//    ): View? {
//        var convertView: View? = convertView
//        if (convertView == null) convertView =
//            layoutInflater.inflate(R.layout.custom_spinner_adapter, parent, false)
//        val user: User? = getItem(position)
//        val textView = convertView.findViewById(R.id.nameTextView) as TextView
//        val imageView: ImageView = convertView.findViewById(R.id.imageIcon) as ImageView
//        textView.setText(user.getName())
//        imageView.setImageResource(user.getImage())
//        return convertView
//    }
//
//    init {
//        layoutInflater = LayoutInflater.from(context)
//    }
//}


data class Guest(val id: Int, val name: String) {


    val image: Int
        get() {
            when (id) {
                0 -> return R.drawable.ic_baseline_man_24
                1 -> return R.drawable.ic_baseline_man_24
                2 -> return R.drawable.ic_baseline_man_24
                3 -> return R.drawable.ic_baseline_man_24
                4 -> return R.drawable.ic_baseline_man_24
                5 -> return R.drawable.ic_baseline_man_24
            }
            return R.drawable.ic_baseline_man_24
        }

}


fun getGuests(): List<Guest> {
    return listOf(
        Guest(1, "1 Гость"),
        Guest(2, "2 Гостя"),
        Guest(3, "3 Гостя"),
        Guest(4, "4 Гостя"),
    )
}