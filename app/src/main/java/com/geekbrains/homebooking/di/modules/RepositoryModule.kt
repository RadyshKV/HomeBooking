package com.geekbrains.homebooking.di.modules

import com.geekbrains.homebooking.domain.*

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsCitiesRepository (impl: CitiesRepositoryImpl): CitiesRepository

    @Singleton
    @Binds
    abstract fun bindsHotelsRepository (impl: HotelsRepositoryImpl): HotelsRepository

    @Singleton
    @Binds
    abstract fun bindsOfferRepository (impl: OffersRepositoryImpl): OffersRepository

    @Singleton
    @Binds
    abstract fun bindsUserRepository (impl: UserRepositoryImpl): UserRepository
}