package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.*
import com.geekbrains.homebooking.remote.RetrofitService
import com.geekbrains.homebooking.remote.model.RegisterRequestBody
import com.geekbrains.homebooking.remote.model.UserRequestBody
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val retrofitService: RetrofitService,
) : UserRepository {

    override fun getUserData(): Single<UserModel> {
        return retrofitService.getUserData("Bearer " + AuthorizationState.token)
    }

    override fun updateUserData(userModel: UserModel): Single<UserModel> {
        TODO("Not yet implemented")
    }

    override fun loginUser(userModel: UserModel): Single<UserToken> {
        return retrofitService.loginUser(UserRequestBody(userModel.email, userModel.password))
    }

    override fun regUser(userModel: UserModel): Single<UserModel> {
        return retrofitService.regUser(
            RegisterRequestBody(
                userModel.email,
                userModel.password,
                userModel.first_name,
                userModel.last_name,
                userModel.middle_name,
                userModel.birthday,
                userModel.passport_number,
                userModel.passport_date,
                userModel.phone,
                userModel.sex,
                userModel.citizen
            )
        )
    }

    override fun getUserBookings(): Single<List<OfferModel>> {
        return retrofitService.getUserBookings("Bearer " + AuthorizationState.token).flatMap { booking ->
            Single.fromCallable {
                val offers = booking.map {
                    OfferModel(
                        it.offer.id,
                        "",
                        it.offer.hotel_id,
                        it.offer.city_id,
                        it.offer.price,
                        it.offer.currency,
                        it.offer.acc_name,
                        it.offer.room_name,
                        it.offer.meal_name,
                        it.offer.meal_code,
                        it.offer.tariff_name,
                        it.offer.date_begin,
                        it.offer.date_end,
                        it.offer.nights,
                        it.offer.quote
                    )
                }
                offers
            }

        }
    }
}