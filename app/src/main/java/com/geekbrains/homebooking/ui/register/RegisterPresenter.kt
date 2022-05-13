package com.geekbrains.homebooking.ui.register

import com.geekbrains.homebooking.navigation.AppScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RegisterPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
) : MvpPresenter<RegisterView>() {

    fun signinPressed(){
        router.replaceScreen(appScreens.loginScreen())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}