package com.geekbrains.homebooking.ui.cities

import com.geekbrains.homebooking.ui.base.BackButtonListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.R
import com.geekbrains.homebooking.databinding.FragmentCitiesBinding
import com.geekbrains.homebooking.ui.cities.adapter.CitiesAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class CitiesFragment : MvpAppCompatFragment(), CitiesView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.appComponent.citiesPresenter()
    }
    private var _binding: FragmentCitiesBinding? = null
    private val binding
        get() = _binding!!

    private val adapter by lazy {
        CitiesAdapter(
            presenter.citiesListRVPresenter,
            //GlideImageLoader()
        )
    }

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

        //binding.citiesFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.citiesFragmentRecyclerView.adapter = adapter
    }

        override fun onResume() {
        super.onResume()
        requireActivity().setTitle(R.string.app_name)
    }

    override fun showLoading() {
        binding.citiesFragmentLoadingLayout.isVisible = true
        binding.citiesFragmentRecyclerView.isVisible = false
    }

    override fun hideLoading() {
        binding.citiesFragmentLoadingLayout.isVisible = false
        binding.citiesFragmentRecyclerView.isVisible = true
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}


//import OnItemViewCityClickListener
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import com.geekbrains.homebooking.R
//import com.geekbrains.homebooking.databinding.FragmentCitiesBinding
//import com.geekbrains.homebooking.model.AppState
//import com.geekbrains.homebooking.model.City
//import com.geekbrains.homebooking.ui.cities.adapter.CitiesAdapter
//import com.geekbrains.homebooking.ui.hotels.HotelsFragment
//
//class CitiesFragment : Fragment() {
//
//    private var _binding: FragmentCitiesBinding? = null
//    private lateinit var viewModel: CitiesViewModel
//    private var adapter: CitiesAdapter? = null
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//
//        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
//                 return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding.citiesFragmentRecyclerView.adapter = adapter
//        viewModel =  ViewModelProvider(this).get(CitiesViewModel::class.java)
//        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
//        viewModel.getDataFromLocalSource()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        requireActivity().setTitle(R.string.app_name)
//    }
//
//    private fun renderData(appState: AppState) = with(binding) {
//        when (appState) {
//            is AppState.SuccessCity -> {
//                citiesFragmentLoadingLayout.visibility = View.GONE
//                adapter = CitiesAdapter(object : OnItemViewCityClickListener {
//                    override fun onItemViewClick(city: City) {
//                        val manager = activity?.supportFragmentManager
//                        manager?.let { manager ->
//                            val bundle = Bundle().apply {
//                                putParcelable(HotelsFragment.BUNDLE_CITY, city)
//                            }
//                            manager.beginTransaction()
//                                .replace(R.id.container, HotelsFragment.newInstance(bundle))
//                                .addToBackStack("")
//                                .commitAllowingStateLoss()
//                        }
//                    }
//                }
//                ).apply {
//                    setCity(appState.cityData)
//                }
//                citiesFragmentRecyclerView.adapter = adapter
//            }
//            is AppState.Loading -> {
//                citiesFragmentLoadingLayout.visibility = View.VISIBLE
//            }
//            is AppState.Error -> {
//                citiesFragmentLoadingLayout.visibility = View.GONE
////                Snackbar
////                    .make(mainFragmentFAB, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
////                    .setAction(getString(R.string.reload)) { viewModel.getWeatherFromLocalSourceRus() }
////                    .show()
//            }
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    companion object {
//        fun newInstance() =
//            CitiesFragment()
//    }
//}