package com.example.kinofan.ui.model

data class FilmDTO(
    val id: Long = 0,
    val title: String?,
    val original_title: String?,
    val genre: String?,
    val vote_average: Double?,
    val year_creation: String?,
    val runtime: Int?,
    val release_date: String?,
    val overview: String?,
    val budget: Long?,
    val revenue: Long?,
    val poster: String?
) {

}