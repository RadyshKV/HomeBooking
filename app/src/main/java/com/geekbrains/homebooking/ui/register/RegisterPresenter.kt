package com.geekbrains.homebooking.ui.register

import android.util.Log
import com.geekbrains.homebooking.domain.UserRepository
import com.geekbrains.homebooking.model.AuthorizationState
import com.geekbrains.homebooking.model.UserModel
import com.geekbrains.homebooking.navigation.AppScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class RegisterPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
    private val userRepository: UserRepository,
) : MvpPresenter<RegisterView>() {

    private val userModel = UserModel()
    private var confirmPassword : String = ""

    private var birthday = Calendar.getInstance()
    private var passportDate = Calendar.getInstance()
    private val dateAPIFormat = "yyyy-MM-dd"
    private val simpleDateAPIFormat = SimpleDateFormat(dateAPIFormat, Locale.getDefault())

    fun signInPressed() {
        router.replaceScreen(appScreens.loginScreen())
    }

    fun registerPressed() {
        if (userModel.email.isNullOrEmpty() || userModel.password.isNullOrEmpty() ||
            userModel.first_name.isNullOrEmpty() || userModel.last_name.isNullOrEmpty() ||
            userModel.birthday.isNullOrEmpty()
        ) {
            viewState.showEmptyMessage()
        } else if (!userModel.password.equals(confirmPassword)) {
            viewState.showPasswordMessage()
        }
        else {
            regUser()
        }
    }

    private fun regUser() {
        userRepository.regUser(userModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                {
                    viewState.hideLoading()
                    router.replaceScreen(
                        appScreens.loginScreen()
                    )
                }, { e ->
                    Log.e("Retrofit", "Ошибка при регистрации пользователя", e)
                    viewState.hideLoading()
                }
            )
    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateView()
    }

    fun updateView() {
        viewState.setEmail(userModel.email.toString())
        viewState.setPassword(userModel.password.toString())
        viewState.setConfirmPassword(confirmPassword)
        viewState.setFirstName(userModel.first_name.toString())
        viewState.setLastName(userModel.last_name.toString())
        viewState.setMiddleName(userModel.middle_name.toString())
        viewState.setBirthday(birthday)
        viewState.setPassportNumber(userModel.passport_number.toString())
        viewState.setPassportDate(passportDate)
        viewState.setPhone(userModel.phone.toString())
        viewState.setSex(userModel.sex.toString())
        viewState.setCitizen(userModel.citizen.toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun setEmail(email: String) {
        userModel.email = email
    }

    fun setPassword(password: String) {
        userModel.password = password
    }

    fun setConfirmPassword(password: String) {
        confirmPassword = password
    }

    fun setFirstName(firstName: String) {
        userModel.first_name = firstName
    }

    fun setLastName(lastName: String) {
        userModel.last_name = lastName
    }

    fun setMiddleName(middleName: String) {
        userModel.middle_name = middleName
    }

    fun setPassportNumber(passportNumber: String) {
        userModel.passport_number = passportNumber
    }

    fun setPhone(phone: String) {
        userModel.phone = phone
    }

    fun setSex(sex: String) {
        userModel.sex = sex
    }

    fun setCitizen(citizen: String) {
        userModel.citizen = citizen
    }

    fun setBirthday(birthday: Calendar) {
        this.birthday.time = birthday.time
        userModel.birthday = simpleDateAPIFormat.format(this.birthday.time)
    }

    fun setPassportDate(passportDate: Calendar) {
        this.passportDate.time = passportDate.time
        userModel.passport_date = simpleDateAPIFormat.format(this.passportDate.time)
    }
}