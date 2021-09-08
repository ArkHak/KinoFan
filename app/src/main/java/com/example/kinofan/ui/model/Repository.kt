package com.example.kinofan.ui.model

interface Repository {
    fun getFilmFromServer(): Film
    fun getFilmFromLocalStorage(): Film
}