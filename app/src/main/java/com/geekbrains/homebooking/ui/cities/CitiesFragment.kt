package com.geekbrains.homebooking.ui.cities

import OnItemViewCityClickListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.geekbrains.homebooking.R
import com.geekbrains.homebooking.databinding.FragmentCitiesBinding
import com.geekbrains.homebooking.model.AppState
import com.geekbrains.homebooking.model.City
import com.geekbrains.homebooking.ui.hotels.HotelsFragment

class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null
    private lateinit var viewModel: CitiesViewModel
    private var adapter: CitiesFragmentAdapter? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
                 return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.citiesFragmentRecyclerView.adapter = adapter
        viewModel =  ViewModelProvider(this).get(CitiesViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getDataFromLocalSource()
    }

    override fun onResume() {
        super.onResume()
        requireActivity().setTitle(R.string.app_name)
    }

    private fun renderData(appState: AppState) = with(binding) {
        when (appState) {
            is AppState.SuccessCity -> {
                citiesFragmentLoadingLayout.visibility = View.GONE
                adapter = CitiesFragmentAdapter(object : OnItemViewCityClickListener {
                    override fun onItemViewClick(city: City) {
                        val manager = activity?.supportFragmentManager
                        manager?.let { manager ->
                            val bundle = Bundle().apply {
                                putParcelable(HotelsFragment.BUNDLE_CITY, city)
                            }
                            manager.beginTransaction()
                                .replace(R.id.container, HotelsFragment.newInstance(bundle))
                                .addToBackStack("")
                                .commitAllowingStateLoss()
                        }
                    }
                }
                ).apply {
                    setCity(appState.cityData)
                }
                citiesFragmentRecyclerView.adapter = adapter
            }
            is AppState.Loading -> {
                citiesFragmentLoadingLayout.visibility = View.VISIBLE
            }
            is AppState.Error -> {
                citiesFragmentLoadingLayout.visibility = View.GONE
//                Snackbar
//                    .make(mainFragmentFAB, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
//                    .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSourceRus() }
//                    .show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() =
            CitiesFragment()
    }
}