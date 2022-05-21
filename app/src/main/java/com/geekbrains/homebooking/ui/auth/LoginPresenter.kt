package com.geekbrains.homebooking.ui.auth

import android.util.Log
import com.geekbrains.homebooking.domain.UserRepository
import com.geekbrains.homebooking.model.AuthorizationState
import com.geekbrains.homebooking.model.UserModel
import com.geekbrains.homebooking.navigation.AppScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject


class LoginPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
    private val userRepository: UserRepository,
) : MvpPresenter<LoginView>() {

    private val userModel = UserModel()

    fun regPressed() {
        router.replaceScreen(appScreens.registerScreen())
    }


    fun loginPressed() {
        if (userModel.email.isNullOrEmpty() || userModel.password.isNullOrEmpty()) {
            viewState.showMessage()
        } else {
            loginUser()
        }
    }

    private fun loginUser() {
        userRepository.loginUser(userModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { userToken ->
                    if (userToken.token?.isNotBlank() == true) {
                        AuthorizationState.isAuthorized = true
                        AuthorizationState.token = userToken.token
                        AuthorizationState.writePref()
                    }
                    viewState.hideLoading()
                    router.replaceScreen(
                        appScreens.accountScreen()
                    )
                }, { e ->
                    Log.e("Retrofit", "Ошибка при получении токена пользователя", e)
                    viewState.hideLoading()
                }
            )
    }

    fun setEmail(email: String) {
        userModel.email = email
    }

    fun setPassword(password: String) {
        userModel.password = password
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateView()
    }

    fun updateView() {
        viewState.setEmail(userModel.email.toString())
        viewState.setPassword(userModel.password.toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}


