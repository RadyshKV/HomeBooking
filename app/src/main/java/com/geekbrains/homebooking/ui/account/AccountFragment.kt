package com.geekbrains.homebooking.ui.account

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.databinding.FragmentAccountBinding
import com.geekbrains.homebooking.model.UserModel
import com.geekbrains.homebooking.ui.account.adapter.BookingAdapter
import com.geekbrains.homebooking.ui.base.BackButtonListener
import com.geekbrains.homebooking.ui.hotels.adapter.HotelsAdapter
import com.geekbrains.homebooking.ui.imageloading.GlideImageLoader
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AccountFragment : MvpAppCompatFragment(), AccountView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.appComponent.accountPresenter()
    }

    private var _binding: FragmentAccountBinding? = null
    private val binding
        get() = _binding!!

    private val adapter by lazy {
        BookingAdapter(
            presenter.bookingsListPresenter,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitButton.setOnClickListener { presenter.signOutPressed() }
        binding.bookingsFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.bookingsFragmentRecyclerView.adapter = adapter
        binding.bookingsFragmentRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )
    }

    override fun showLoading() {
        binding.accountFragmentLoadingLayout.isVisible = true
        binding.bookingsFragmentRecyclerView.isVisible = false
    }

    override fun hideLoading() {
        binding.accountFragmentLoadingLayout.isVisible = false
        binding.bookingsFragmentRecyclerView.isVisible = true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateList() {
        adapter.notifyDataSetChanged()
    }

    override fun updateUserData(userModel: UserModel) {
        binding.firstName.text = userModel.first_name
        binding.lastName.text = userModel.last_name
        binding.middleName.text = userModel.middle_name
        binding.birthday.text = userModel.birthday
        binding.email.text = userModel.email
        binding.phone.text = userModel.phone
        binding.passportNumber.text = userModel.passport_number
        binding.passportDate.text = userModel.passport_date
        binding.sex.text = userModel.sex
        binding.citizen.text = userModel.citizen
    }

    override fun backPressed() = presenter.backPressed()

}