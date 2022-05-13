package com.geekbrains.homebooking.ui.main

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

        router.replaceScreen(appScreens.citiesScreen())
    }

    fun findButtonPressed(){
        router.replaceScreen(appScreens.citiesScreen())
    }

    fun signinButtonPressed(){
        router.navigateTo(appScreens.loginScreen())
    }

    fun backPressed(){
        router.exit()
    }
}