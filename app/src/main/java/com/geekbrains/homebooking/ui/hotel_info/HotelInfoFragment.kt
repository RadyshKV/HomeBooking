package com.geekbrains.homebooking.ui.hotel_info

import android.annotation.SuppressLint
import android.content.Context
import com.geekbrains.homebooking.ui.base.BackButtonListener
import com.geekbrains.homebooking.model.HotelModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.databinding.FragmentHotelInfoBinding
import com.geekbrains.homebooking.model.OfferModel
import com.geekbrains.homebooking.ui.hotel_info.adapter.OffersRecyclerViewAdapter
import com.geekbrains.homebooking.ui.imageloading.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class HotelInfoFragment : MvpAppCompatFragment(), HotelInfoView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.appComponent.hotelInfoPresenterFactory().presenter(hotelModel)
    }

    private val imageLoader = GlideImageLoader()

    private var _binding: FragmentHotelInfoBinding? = null
    private val binding
        get() = _binding!!

    private val adapter by lazy {
        OffersRecyclerViewAdapter(
            presenter.offersListPresenter,
        )
    }

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
        binding.offersFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.offersFragmentRecyclerView.adapter = adapter
        binding.offersFragmentRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )
    }

    private fun showSoftwareKeyboard(showKeyboard: Boolean) {
        val inputManager: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            if (showKeyboard) InputMethodManager.SHOW_FORCED else InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = hotelModel.name
        showSoftwareKeyboard(false)
    }

    override fun backPressed() = presenter.backPressed()

    override fun setHotelAddress(hotelAddress: String?) {
        binding.hotelAddress.text = hotelAddress
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun loadImage(url: String?) {
        imageLoader.loadInto(url, binding.imageHotel)
    }

    override fun showDialog(offerModel: OfferModel) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(offerModel.room_name)
        builder.setMessage("Забронировать за " + offerModel.price + " " + offerModel.currency + "?")

        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            presenter.booking(offerModel)

        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, which ->
            Toast.makeText(
                requireContext(),
                "Бронирование отменено", Toast.LENGTH_SHORT
            ).show()
        }
        builder.show()
    }

    override fun showMessage() {
        Toast.makeText(
            requireContext(),
            "Вы успешно забронировали номер", Toast.LENGTH_SHORT
        ).show()
        Toast.makeText(
            requireContext(),
            "Ваши брони находятся в личном кабинете", Toast.LENGTH_SHORT
        ).show()
    }

    override fun showLoading() {
        //binding.hotelInfoFragmentLoadingLayout.isVisible = true
    }

    override fun hideLoading() {
        //binding.hotelInfoFragmentLoadingLayout.isVisible = false
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


