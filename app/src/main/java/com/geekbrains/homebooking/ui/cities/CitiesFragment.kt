package com.geekbrains.homebooking.ui.cities

import com.geekbrains.homebooking.ui.base.BackButtonListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.R
import com.geekbrains.homebooking.databinding.FragmentCitiesBinding
import com.geekbrains.homebooking.ui.cities.adapter.CitiesRecyclerViewAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import com.geekbrains.homebooking.ui.cities.adapter.CitiesListAdapter

class CitiesFragment : MvpAppCompatFragment(), CitiesView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.appComponent.citiesPresenter()
    }
    private var _binding: FragmentCitiesBinding? = null
    private val binding
        get() = _binding!!


    private val recyclerViewAdapter by lazy {
        CitiesRecyclerViewAdapter(
            presenter.citiesRecyclerViewPresenter,
        )
    }

    private val listAdapter by lazy {
        CitiesListAdapter(
            requireActivity(),
            presenter.citiesListPresenter,
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


        binding.inputCityText.setAdapter(listAdapter)
        binding.inputCityText.threshold = 1
        binding.citiesFragmentRecyclerView.adapter = recyclerViewAdapter
        binding.inputCityText.setOnItemClickListener { parent, _, position, id ->
            presenter.citiesListPresenter.itemClickListener?.let {
                it(parent.getItemAtPosition(position).toString())
            }
        }
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
        if (listAdapter.isEmpty) listAdapter.addAll(presenter.citiesListPresenter.getCitiesNameList())
        listAdapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}