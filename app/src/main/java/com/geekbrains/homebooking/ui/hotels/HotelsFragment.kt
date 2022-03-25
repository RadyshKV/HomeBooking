package com.geekbrains.homebooking.ui.hotels

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrains.homebooking.databinding.FragmentHottelsBinding
import com.geekbrains.homebooking.model.City

@Suppress("NAME_SHADOWING")
class HotelsFragment : Fragment() {
    private lateinit var binding: FragmentHottelsBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = FragmentHottelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val city = arguments?.getParcelable<City>(BUNDLE_EXTRA)
        city?.let { city ->
            val city = city.city
//            binding.cityName.text = city.city
//            binding.cityCoordinates.text = String.format(
//                    getString(R.string.city_coordinates),
//                    city.lat.toString(),
//                    city.lon.toString()
//            )
//            binding.temperatureValue.text = weather.temperature.toString()
//            binding.feelsLikeValue.text = weather.feelsLike.toString()
        }
    }

    companion object {
        const val BUNDLE_EXTRA = "city"

        fun newInstance(bundle: Bundle): HotelsFragment {
            val fragment = HotelsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}