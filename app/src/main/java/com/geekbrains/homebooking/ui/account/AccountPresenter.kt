package com.geekbrains.homebooking.ui.account

import android.util.Log
import com.geekbrains.homebooking.domain.BookingRepository
import com.geekbrains.homebooking.domain.UserRepository
import com.geekbrains.homebooking.model.*
import com.geekbrains.homebooking.navigation.AppScreens
import com.geekbrains.homebooking.ui.base.IListPresenter
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import javax.inject.Inject


class AccountPresenter @Inject constructor(
    private val router: Router,
    private val appScreens: AppScreens,
    private val userRepository: UserRepository,
    private val bookingRepository: BookingRepository,
) : MvpPresenter<AccountView>() {

    val bookingsListPresenter = BookingsListPresenter()

    private var userModel = UserModel()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadUserData()
        loadUserBookings()
        bookingsListPresenter.itemClickListener = {
            deleteBooking(bookingsListPresenter.bookings[it.pos])
        }
    }

    private fun deleteBooking(offerModel: OfferModel) {
        bookingRepository.deleteBooking(offerModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                {
                    viewState.hideLoading()
                    loadUserBookings()
                }, { e ->
                    Log.e("Retrofit", "Ошибка при удалении брони", e)
                    viewState.hideLoading()
                }
            )

    }

    private fun loadUserData() {
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
                    signOutPressed()
                }
            )
    }

    private fun loadUserBookings() {
        userRepository.getUserBookings()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { viewState.showLoading() }
            .subscribe(
                { offers ->
                    if (offers.isNotEmpty()){
                        bookingsListPresenter.bookings.addAll(offers)
                    }
                    viewState.updateList()
                    viewState.hideLoading()
                }, { e ->
                    Log.e("Retrofit", "Ошибка при получении бронирования", e)
                    viewState.hideLoading()
                }
            )
    }

    class BookingsListPresenter : IListPresenter<BookingItemView> {
        val bookings = mutableListOf<OfferModel>()

        override var itemClickListener: ((BookingItemView) -> Unit)? = {}

        override fun bindView(view: BookingItemView) {
            val booking = bookings[view.pos]
            view.setAccName(booking.acc_name)
            view.setRoomName(booking.room_name)
            view.setCurrency(booking.currency)
            view.setDateBegin(booking.date_begin)
            view.setDateEnd(booking.date_end)
            view.setMealName(booking.meal_name)
            view.setNights(booking.nights)
            view.setPrice(booking.price)
            view.setListener()
        }
        override fun getCount() = bookings.size

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