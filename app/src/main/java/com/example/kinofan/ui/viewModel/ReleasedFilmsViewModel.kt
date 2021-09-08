package com.example.kinofan.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinofan.ui.model.Repository
import com.example.kinofan.ui.model.RepositoryImpl
import kotlin.random.Random

class ReleasedFilmsViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val repository: Repository = RepositoryImpl()
    val livaData: LiveData<AppState> = liveDataToObserve

    fun getFilmFromLocalSource() = getDataFromLocalSource()
    fun getFilmFromRemoteSource() = getDataFromRemoteSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(3000)
            liveDataToObserve.postValue(AppState.Success(repository.getFilmFromLocalStorage()))
        }.start()
    }

    private fun getDataFromRemoteSource(){
        liveDataToObserve.value = AppState.Loading
        Thread {
            Thread.sleep(3000)
            if (Random.nextBoolean()) {
                liveDataToObserve.postValue(AppState.Success(repository.getFilmFromServer()))
            } else {
                liveDataToObserve.postValue(AppState.Error(Exception("Нет интернета")))
            }
        }.start()
    }
}