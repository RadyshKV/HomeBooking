package com.geekbrains.homebooking.ui.hotels

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import com.geekbrains.homebooking.ui.base.BackButtonListener
import com.geekbrains.homebooking.model.CityModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.databinding.FragmentHotelsBinding
import com.geekbrains.homebooking.ui.hotels.adapter.GuestsAdapter
import com.geekbrains.homebooking.ui.hotels.adapter.HotelsAdapter
import com.geekbrains.homebooking.ui.hotels.adapter.getGuests
import com.geekbrains.homebooking.ui.imageloading.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.text.SimpleDateFormat
import java.util.*



class HotelsFragment : MvpAppCompatFragment(), HotelsView, BackButtonListener {
    private var dateIn = Calendar.getInstance()
    private var dateOut = Calendar.getInstance()
    private val dateFormat = "dd.MM.yyyy" // mention the format you need
    private val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())


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

    // установка обработчика выбора даты
    private var dateInListener =
        OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->

            setInitialDateIn(year, monthOfYear, dayOfMonth)
        }

    // установка обработчика выбора даты
    private var dateOutListener =
        OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->

            setInitialDateOut(year, monthOfYear, dayOfMonth)
        }

    // отображаем диалоговое окно для выбора даты
    private fun setDate(v: View?, u: OnDateSetListener, date: Calendar) {
        DatePickerDialog(
            requireContext(), u,
            date[Calendar.YEAR],
            date[Calendar.MONTH],
            date[Calendar.DAY_OF_MONTH]
        )
            .show()
    }

    // установка начальных даты и времени
    private fun setInitialDateIn(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        dateIn[Calendar.YEAR] = year
        dateIn[Calendar.MONTH] = monthOfYear
        dateIn[Calendar.DAY_OF_MONTH] = dayOfMonth
        binding.inputDateIn.setText(simpleDateFormat.format(dateIn.time))
    }

    // установка начальных даты и времени
    private fun setInitialDateOut(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        dateOut[Calendar.YEAR] = year
        dateOut[Calendar.MONTH] = monthOfYear
        dateOut[Calendar.DAY_OF_MONTH] = dayOfMonth
        binding.inputDateOut.setText(simpleDateFormat.format(dateOut.time))
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
        binding.spinnerGuests.adapter = GuestsAdapter(requireContext(), getGuests())
        binding.hotelsFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.hotelsFragmentRecyclerView.adapter = adapter
        binding.hotelsFragmentRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        binding.dateIn.setEndIconOnClickListener {
            setDate(it, dateInListener, dateIn)
        }

        binding.dateOut.setEndIconOnClickListener {
            setDate(it, dateOutListener, dateOut)
        }

        binding.inputDateIn.setOnClickListener {
            setDate(it, dateInListener, dateIn)
        }
        binding.inputDateOut.setOnClickListener {
            setDate(it, dateOutListener, dateOut)
        }
        binding.buttonFind.setOnClickListener {  }
    }

    override fun onResume() {
        super.onResume()
        requireActivity().title = cityModel.name ?: cityModel.resort_name ?: cityModel.region_name
        showSoftwareKeyboard(false)
    }

    private fun showSoftwareKeyboard(showKeyboard: Boolean) {
        val inputManager: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            if (showKeyboard) InputMethodManager.SHOW_FORCED else InputMethodManager.HIDE_NOT_ALWAYS
        )
    }
    override fun showLoading() {
        binding.hotelsFragmentLoadingLayout.isVisible = true
        binding.hotelsFragmentRecyclerView.isVisible = false
    }

    override fun hideLoading() {
        binding.hotelsFragmentLoadingLayout.isVisible = false
        binding.hotelsFragmentRecyclerView.isVisible = true
    }

    @SuppressLint("NotifyDataSetChanged")
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