package com.example.kinofan.ui.model

class RepositoryImpl : Repository {
    override fun getFilmFromServer(): Film = Film("Star Wars", rating = 10.0)

    override fun getFilmFromLocalStorageReleased(): List<Film> = getReleasedFilms()
    override fun getFilmFromLocalStorageUpcoming(): List<Film> = getUpcomingFilms()

}