package com.geekbrains.homebooking.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.databinding.FragmentAuthBinding
import com.geekbrains.homebooking.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class LoginFragment : MvpAppCompatFragment(), LoginView, BackButtonListener {

    private val presenter by moxyPresenter {
        App.instance.appComponent.loginPresenter()
    }
    private var _binding: FragmentAuthBinding? = null
    private val binding
        get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RegisterButton.setOnClickListener {
            presenter.regPressed()
        }
        binding.email.doAfterTextChanged { presenter.setEmail(binding.email.text.toString()) }
        binding.password.doAfterTextChanged { presenter.setPassword(binding.password.text.toString()) }
    }



    override fun backPressed() = presenter.backPressed()

    override fun setEmail(email: String) {
        binding.email.setText(email)
    }

    override fun setPassword(password: String) {
        binding.password.setText(password)
    }


}