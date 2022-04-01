package com.geekbrains.homebooking.ui.hotels

import OnItemViewHotelClickListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.homebooking.R
import com.geekbrains.homebooking.databinding.FragmentHotelsBinding
import com.geekbrains.homebooking.model.AppState
import com.geekbrains.homebooking.model.City
import com.geekbrains.homebooking.model.Hotel
import com.geekbrains.homebooking.ui.hotel_info.HotelInfoFragment
import com.geekbrains.homebooking.MainActivity

import android.app.DatePickerDialog
import java.util.*


class HotelsFragment : Fragment() {

    private lateinit var _binding: FragmentHotelsBinding
    private lateinit var viewModel: HotelsViewModel
    private var adapter: HotelsFragmentAdapter? = null

    private val binding get() = _binding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    // отображаем диалоговое окно для выбора даты
    fun setDate(view: View) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(requireContext(), { view, year, monthOfYear, dayOfMonth -> }, year, month, day)
        dpd.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val city = arguments?.getParcelable<City>(BUNDLE_CITY)
        city?.let { city ->
            val city = city.city
            requireActivity().setTitle(city)
//            binding.cityName.text = city.city
//            binding.cityCoordinates.text = String.format(
//                    getString(R.string.city_coordinates),
//                    city.lat.toString(),
//                    city.lon.toString()
//            )
//            binding.temperatureValue.text = weather.temperature.toString()
//            binding.feelsLikeValue.text = weather.feelsLike.toString()
        }
        binding.hotelsFragmentRecyclerView.adapter = adapter
        viewModel =  ViewModelProvider(this).get(HotelsViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        city?.let { viewModel.getDataFromLocalSource(it) }
        binding.dateIn.setEndIconOnClickListener {
            setDate(it)
        }
    }


    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.SuccessHotel -> {
                hotelsFragmentLoadingLayout.visibility = View.GONE
                adapter = HotelsFragmentAdapter(object : OnItemViewHotelClickListener {
                    override fun onItemViewClick(hotel: Hotel) {
                        val manager = activity?.supportFragmentManager
                        manager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(HotelInfoFragment.BUNDLE_HOTEL, hotel)
                            }
                            manager.beginTransaction()
                                .replace(R.id.container, HotelInfoFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setHotel(appState.hotelData)
                }
                hotelsFragmentRecyclerView.adapter = adapter
            }
            is AppState.Loading -> {
                hotelsFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                hotelsFragmentLoadingLayout.visibility = View.GONE
//                Snackbar
//                    .make(mainFragmentFAB, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
//                    .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSourceRus() }
//                    .show()
            }
        }
    }

    companion object {
        const val BUNDLE_CITY = "city"

        fun newInstance(bundle: Bundle): HotelsFragment {
            val fragment = HotelsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}