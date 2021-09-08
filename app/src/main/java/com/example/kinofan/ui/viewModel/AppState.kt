package com.example.kinofan.ui.viewModel

import com.example.kinofan.ui.model.Film

sealed class AppState {
    data class Success(val filmsData: List<Film>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}