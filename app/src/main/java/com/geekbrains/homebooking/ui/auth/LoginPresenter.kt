package com.geekbrains.homebooking.ui.auth


import com.geekbrains.homebooking.navigation.AppScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import moxy.viewstate.strategy.alias.AddToEndSingle
import javax.inject.Inject

class LoginPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
) : MvpPresenter<LoginView>() {

    private var eMail: String = ""
    private var password: String = ""

    fun regPressed(){
        router.replaceScreen(appScreens.registerScreen())
    }

    fun setEmail(email: String){
        eMail = email
    }


    fun setPassword(password: String){
        this.password = password
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.setEmail(eMail)
        viewState.setPassword(password)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}