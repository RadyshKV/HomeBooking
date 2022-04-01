package com.geekbrains.homebooking.ui.hotels

import Repository
import RepositoryImpl
import androidx.lifecycle.*
import com.geekbrains.homebooking.model.AppState
import com.geekbrains.homebooking.model.City

class HotelsViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) : ViewModel(), LifecycleObserver {

    private val repository: Repository = RepositoryImpl()

    private val lifeCycleLiveData = MutableLiveData<String>()

    fun getLiveData() = liveDataToObserve

    fun getLifeCycleData() = lifeCycleLiveData


    fun getDataFromLocalSource(city: City) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(AppState.SuccessHotel(
                repository.getHotelFromLocalStorage(city))
            )
            //liveDataToObserve.postValue(AppState.Error())
        }.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onViewStart() {
        lifeCycleLiveData.value = "Start"
    }

}