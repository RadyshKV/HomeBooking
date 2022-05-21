package com.geekbrains.homebooking.ui.main

import com.geekbrains.homebooking.model.AuthorizationState
import com.geekbrains.homebooking.navigation.AppScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
): MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        AuthorizationState.readPref()
        router.replaceScreen(appScreens.citiesScreen())
    }

    fun findButtonPressed(){
        router.replaceScreen(appScreens.citiesScreen())
    }

    fun signinButtonPressed(){
        if (AuthorizationState.isAuthorized == true)  {
            router.navigateTo(appScreens.accountScreen())

        } else {
            router.navigateTo(appScreens.loginScreen())
        }
    }

    fun backPressed(){
        router.exit()
    }
}