package com.geekbrains.homebooking.ui.account

import android.util.Log
import com.geekbrains.homebooking.domain.UserRepository
import com.geekbrains.homebooking.model.AuthorizationState
import com.geekbrains.homebooking.model.UserModel
import com.geekbrains.homebooking.navigation.AppScreens
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter


class AccountPresenter @AssistedInject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
    private val userRepository: UserRepository,
    @Assisted("userModel") private var userModel: UserModel,
) : MvpPresenter<AccountView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }


    private fun loadData() {
        userRepository.getUserData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { user ->
                    userModel = user
                    viewState.updateUserData(userModel)
                    viewState.hideLoading()
                }, { e ->
                    Log.e("Retrofit", "Ошибка при получении данных пользователя", e)
                    viewState.hideLoading()
                }
            )
    }


    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    fun signOutPressed() {
        AuthorizationState.isAuthorized = false
        AuthorizationState.token = ""
        AuthorizationState.writePref()
        router.replaceScreen(appScreens.loginScreen())
    }
}

@AssistedFactory
interface AccountPresenterFactory {
    fun presenter(
        @Assisted("userModel") userModel: UserModel
    ): AccountPresenter
}
