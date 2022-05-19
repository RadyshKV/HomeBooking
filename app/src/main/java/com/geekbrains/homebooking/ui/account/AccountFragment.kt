package com.geekbrains.homebooking.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.databinding.FragmentAccountBinding
import com.geekbrains.homebooking.model.UserModel
import com.geekbrains.homebooking.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class AccountFragment : MvpAppCompatFragment(), AccountView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.appComponent.accountPresenterFactory().presenter(userModel)
    }

    private var _binding: FragmentAccountBinding? = null
    private val binding
        get() = _binding!!

    private val userModel by lazy {
        requireArguments().getSerializable(AccountFragment.USER_MODEL) as UserModel
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

    }

    override fun showLoading() {
        binding.accountFragmentLoadingLayout.isVisible = true
    }

    override fun hideLoading() {
        binding.accountFragmentLoadingLayout.isVisible = false
    }

    override fun updateUserData(userModel: UserModel) {
        binding.myData.text = userModel.toString()
    }

    override fun backPressed() = presenter.backPressed()

    companion object {
        private const val USER_MODEL = "USER_MODEL"
        fun newInstance(userModel: UserModel): AccountFragment {
            return AccountFragment().apply {
                arguments = bundleOf(USER_MODEL to userModel)
            }
        }
    }
}