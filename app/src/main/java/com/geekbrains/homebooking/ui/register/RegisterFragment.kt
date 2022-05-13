package com.geekbrains.homebooking.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.databinding.FragmentRegisterBinding
import com.geekbrains.homebooking.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RegisterFragment : MvpAppCompatFragment(), RegisterView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.appComponent.registerPresenter()
    }
    private var _binding: FragmentRegisterBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.SignInButton.setOnClickListener {
            presenter.signinPressed()
        }
    }

    override fun backPressed() = presenter.backPressed()


}