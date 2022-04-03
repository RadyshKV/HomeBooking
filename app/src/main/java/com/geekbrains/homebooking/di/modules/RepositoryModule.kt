package com.geekbrains.homebooking.di.modules

import com.geekbrains.homebooking.domain.HotelsRepository
import com.geekbrains.homebooking.domain.HotelsRepositoryImpl
import com.geekbrains.homebooking.domain.CitiesRepository
import com.geekbrains.homebooking.domain.CitiesRepositoryImpl

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

}