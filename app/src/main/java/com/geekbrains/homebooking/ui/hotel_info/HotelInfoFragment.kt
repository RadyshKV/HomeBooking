package com.geekbrains.homebooking.ui.hotel_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.homebooking.databinding.FragmentHotelInfoBinding
import com.geekbrains.homebooking.model.Hotel


class HotelInfoFragment : Fragment() {

    private lateinit var hotelInfoViewModel: HotelInfoViewModel
    private var _binding: FragmentHotelInfoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        hotelInfoViewModel =
            ViewModelProvider(this).get(HotelInfoViewModel::class.java)
        val hotel = arguments?.getParcelable<Hotel>(BUNDLE_HOTEL)
        _binding = FragmentHotelInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root
        hotel?.let { hotel ->
            val hotel = hotel.hotel
            binding.textDashboard.text = hotel
            requireActivity().setTitle(hotel)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val BUNDLE_HOTEL = "hotel"

        fun newInstance(bundle: Bundle): HotelInfoFragment {
            val fragment = HotelInfoFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}