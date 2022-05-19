package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.AuthorizationState
import com.geekbrains.homebooking.model.UserModel
import com.geekbrains.homebooking.model.UserToken
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
}