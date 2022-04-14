package com.geekbrains.homebooking.ui.hotel_info

import com.geekbrains.homebooking.ui.base.BackButtonListener
import com.geekbrains.homebooking.model.HotelModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.databinding.FragmentHotelInfoBinding
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class HotelInfoFragment() : MvpAppCompatFragment(), HotelInfoView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.appComponent.hotelInfoPresenterFactory().presenter(hotelModel)
    }
    private var _binding: FragmentHotelInfoBinding? = null
    private val binding
        get() = _binding!!

    private val hotelModel by lazy {
        requireArguments().getSerializable(HOTEL_MODEL) as HotelModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun backPressed() = presenter.backPressed()

    override fun setHotelName(hotelName: String?) {
        binding.hotelName.text = hotelName
    }

    override fun setHotelType(hotelType: String?) {
        binding.hotelType.text = hotelType
    }

    companion object {
        private const val HOTEL_MODEL = "HOTEL_MODEL"
        fun newInstance(hotelModel: HotelModel): HotelInfoFragment {
            return HotelInfoFragment().apply {
                arguments = bundleOf(HOTEL_MODEL to hotelModel)
            }
        }
    }
}


