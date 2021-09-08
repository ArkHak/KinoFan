package com.example.kinofan.ui.model

interface Repository {
    fun getFilmFromServer(): Film
    fun getFilmFromLocalStorage(): Film
//    fun getFilmFromLocalStorageReleased(): Film
//    fun getFilmFromLocalStorageUpcoming(): Film
}