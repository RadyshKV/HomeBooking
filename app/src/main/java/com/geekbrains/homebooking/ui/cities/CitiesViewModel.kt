package com.geekbrains.homebooking.ui.cities

import Repository
import RepositoryImpl
import androidx.lifecycle.*
import com.geekbrains.homebooking.model.AppState

class CitiesViewModel(private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()) : ViewModel(), LifecycleObserver {

    private val repository: Repository = RepositoryImpl()

    private val lifeCycleLiveData = MutableLiveData<String>()

    fun getLiveData() = liveDataToObserve
    fun getData(): LiveData<AppState> {
        getDataFromLocalSource()
        return liveDataToObserve
    }

    fun getLifeCycleData() = lifeCycleLiveData

    fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(1000)
            liveDataToObserve.postValue(AppState.SuccessCity(
                repository.getCityFromLocalStorage())
            )
            //liveDataToObserve.postValue(AppState.Error())
        }.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onViewStart() {
        lifeCycleLiveData.value = "Start"
    }

}