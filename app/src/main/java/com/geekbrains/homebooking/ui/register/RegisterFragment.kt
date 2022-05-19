package com.geekbrains.homebooking.ui.register

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.geekbrains.homebooking.App
import com.geekbrains.homebooking.databinding.FragmentRegisterBinding
import com.geekbrains.homebooking.ui.base.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import java.text.SimpleDateFormat
import java.util.*

class RegisterFragment : MvpAppCompatFragment(), RegisterView, BackButtonListener {
    private val dateViewFormat = "dd.MM.yyyy"
    private val simpleDateViewFormat = SimpleDateFormat(dateViewFormat, Locale.getDefault())
    private val calendar = Calendar.getInstance()


    private val presenter by moxyPresenter {
        App.instance.appComponent.registerPresenter()
    }
    private var _binding: FragmentRegisterBinding? = null
    private val binding
        get() = _binding!!

    // установка обработчика выбора даты
    private var birthdayListener =
        OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            calendar.set(year, monthOfYear, dayOfMonth)
            presenter.setBirthday(calendar)
            setBirthday(calendar)
        }

    // установка обработчика выбора даты
    private var passportDateListener =
        OnDateSetListener { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            calendar.set(year, monthOfYear, dayOfMonth)
            presenter.setPassportDate(calendar)
            setPassportDate(calendar)
        }

    // отображаем диалоговое окно для выбора даты
    private fun setDate(v: View?, u: DatePickerDialog.OnDateSetListener, date: Calendar) {
        DatePickerDialog(
            requireContext(), u,
            date[Calendar.YEAR],
            date[Calendar.MONTH],
            date[Calendar.DAY_OF_MONTH]
        )
            .show()
    }

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
            presenter.signInPressed()
        }

        binding.submitButton.setOnClickListener {
            presenter.registerPressed()
        }

        binding.email.doAfterTextChanged { presenter.setEmail(binding.email.text.toString()) }
        binding.password.doAfterTextChanged { presenter.setPassword(binding.password.text.toString()) }
        binding.firstName.doAfterTextChanged { presenter.setFirstName(binding.firstName.text.toString()) }
        binding.lastName.doAfterTextChanged { presenter.setLastName(binding.lastName.text.toString()) }
        binding.middleName.doAfterTextChanged { presenter.setMiddleName(binding.middleName.text.toString()) }
        binding.passportNumber.doAfterTextChanged { presenter.setPassportNumber(binding.passportNumber.text.toString()) }
        binding.phone.doAfterTextChanged { presenter.setPhone(binding.phone.text.toString()) }
        binding.sex.doAfterTextChanged { presenter.setSex(binding.sex.text.toString()) }
        binding.citizen.doAfterTextChanged { presenter.setCitizen(binding.citizen.text.toString()) }

        binding.confirmPassword.doAfterTextChanged { presenter.setConfirmPassword(binding.confirmPassword.text.toString()) }

        binding.birthdayLayout.setEndIconOnClickListener {
            calendar.time = simpleDateViewFormat.parse(binding.birthday.text.toString())!!
            setDate(it, birthdayListener, calendar)
        }

        binding.passportDateLayout.setEndIconOnClickListener {
            calendar.time = simpleDateViewFormat.parse(binding.passportDate.text.toString())!!
            setDate(it, passportDateListener, calendar)
        }

        binding.birthday.setOnClickListener {
            calendar.time = simpleDateViewFormat.parse(binding.birthday.text.toString())!!
            setDate(it, birthdayListener, calendar)
        }
        binding.passportDate.setOnClickListener {
            calendar.time = simpleDateViewFormat.parse(binding.passportDate.text.toString())!!
            setDate(it, passportDateListener, calendar)
        }
    }

    override fun onPause() {
        presenter.updateView()
        super.onPause()
    }

    override fun showEmptyMessage() {
        Toast.makeText(requireContext(), "Необходимо заполнить обязательные поля",
            Toast.LENGTH_SHORT).show()
    }

    override fun showPasswordMessage() {
        Toast.makeText(requireContext(), "Пароли не совпадают",
            Toast.LENGTH_SHORT).show()
    }

    override fun setEmail(email: String) {
        binding.email.setText(email)
    }

    override fun setPassword(password: String) {
        binding.password.setText(password)
    }

    override fun setConfirmPassword(password: String) {
        binding.confirmPassword.setText(password)
    }

    override fun setFirstName(firstName: String) {
        binding.firstName.setText(firstName)
    }

    override fun setLastName(lastName: String) {
        binding.lastName.setText(lastName)
    }

    override fun setMiddleName(middleName: String) {
        binding.middleName.setText(middleName)
    }

    override fun setPassportNumber(passportNumber: String) {
        binding.passportNumber.setText(passportNumber)
    }

    override fun setPhone(phone: String) {
        binding.phone.setText(phone)
    }

    override fun setSex(sex: String) {
        binding.sex.setText(sex)
    }

    override fun setCitizen(citizen: String) {
        binding.citizen.setText(citizen)
    }

    override fun setBirthday(date: Calendar) {
        binding.birthday.setText(simpleDateViewFormat.format(date.time))
    }

    override fun setPassportDate(date: Calendar) {
        binding.passportDate.setText(simpleDateViewFormat.format(date.time))
    }

    override fun showLoading() {
        binding.registerFragmentLoadingLayout.isVisible = true
    }

    override fun hideLoading() {
        binding.registerFragmentLoadingLayout.isVisible = false
    }

    override fun backPressed() = presenter.backPressed()


}