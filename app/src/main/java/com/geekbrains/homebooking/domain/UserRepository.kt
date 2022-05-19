package com.geekbrains.homebooking.domain

import com.geekbrains.homebooking.model.UserModel
import com.geekbrains.homebooking.model.UserToken
import io.reactivex.rxjava3.core.Single

interface UserRepository {

    fun getUserData(): Single<UserModel>

    fun updateUserData(userModel: UserModel): Single<UserModel>

    fun loginUser(userModel: UserModel): Single<UserToken>

    fun regUser(userModel: UserModel): Single<UserModel>
}