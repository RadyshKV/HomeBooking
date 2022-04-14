package com.geekbrains.homebooking.ui.hotels

import com.geekbrains.homebooking.ui.base.BackButtonListener
import com.geekbrains.homebooking.model.CityModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.databinding.FragmentHotelsBinding
import com.geekbrains.homebooking.ui.hotels.adapter.HotelsAdapter
import com.geekbrains.homebooking.ui.imageloading.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class HotelsFragment : MvpAppCompatFragment(), HotelsView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.appComponent.hotelsPresenterFactory().presenter(cityModel)
    }
    private var _binding: FragmentHotelsBinding? = null
    private val binding
        get() = _binding!!

    private val adapter by lazy {
        HotelsAdapter(
            presenter.hotelsListPresenter,
            GlideImageLoader()
        )
    }

    private val cityModel: CityModel by lazy {
        requireArguments().getSerializable(KEY_CITY_MODEL) as CityModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.hotelsFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.hotelsFragmentRecyclerView.adapter = adapter
        binding.hotelsFragmentRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )

    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = cityModel.name ?: cityModel.resort_name ?: cityModel.region_name
    }

    override fun showLoading() {
        binding.hotelsFragmentLoadingLayout.isVisible = true
        binding.hotelsFragmentRecyclerView.isVisible = false
    }

    override fun hideLoading() {
        binding.hotelsFragmentLoadingLayout.isVisible = false
        binding.hotelsFragmentRecyclerView.isVisible = true
    }

    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        private const val KEY_CITY_MODEL = "KEY_CITY_MODEL"
        fun newInstance(cityModel: CityModel?): HotelsFragment {
            return HotelsFragment().apply {
                arguments = bundleOf(KEY_CITY_MODEL to cityModel)
            }
        }
    }
}