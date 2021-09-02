package com.example.kinofan.ui.films

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FilmsViewModel : ViewModel() {

    private val liveDataToObserve: MutableLiveData<String> = MutableLiveData()

    fun getData(): LiveData<String> {
        getDataFromLocalSource()
        return liveDataToObserve
    }

    private fun getDataFromLocalSource() {
        Thread {
            Thread.sleep(3000)
            liveDataToObserve.postValue("Начало")
            Thread.sleep(3000)
            liveDataToObserve.postValue("Мстители")
            Thread.sleep(3000)
            liveDataToObserve.postValue("Унесенные призраками")
        }.start()
    }
}