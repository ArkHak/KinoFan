package com.example.kinofan.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.kinofan.ui.data.repository.NetworkState
import com.example.kinofan.ui.data.vo.Movie
import com.example.kinofan.ui.popular_movie.MoviePagedListRepository
import io.reactivex.disposables.CompositeDisposable

class PopularFilmsViewModel(private val movieRepository: MoviePagedListRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val moviePagedList: LiveData<PagedList<Movie>> by lazy {
        movieRepository.fetchLiveMoviePagedList(compositeDisposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        movieRepository.getNetworkState()
    }

    fun listIsEmpty(): Boolean = moviePagedList.value?.isEmpty() ?: true

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}