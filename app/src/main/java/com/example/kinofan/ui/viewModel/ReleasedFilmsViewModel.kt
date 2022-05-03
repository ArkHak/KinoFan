package com.example.kinofan.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinofan.ui.model.Repository
import com.example.kinofan.ui.model.RepositoryImpl
import kotlin.random.Random

class ReleasedFilmsViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: Repository = RepositoryImpl()
) : ViewModel() {

    fun getLiveDate() = liveDataToObserve

    fun getFilmsFromLocalSourceReleased() = getDataFromLocalSource()

    fun getFilmsFromRemoteSourceReleased() = getDataFromLocalSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            liveDataToObserve
                .postValue(AppState.Success(repositoryImpl.getFilmFromLocalStorageReleased()))
        }.start()
    }
}