package com.example.kinofan.ui.model

class RepositoryImpl : Repository {
    override fun getFilmFromServer() = Film()

    override fun getFilmFromLocalStorageReleased() = getReleasedFilms()
    override fun getFilmFromLocalStorageUpcoming() = getUpcomingFilms()
}