package com.example.kinofan.ui.model

interface Repository {
    fun getFilmFromServer(): Film
    fun getFilmFromLocalStorageReleased(): List<Film>
    fun getFilmFromLocalStorageUpcoming(): List<Film>
}