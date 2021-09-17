package com.example.kinofan.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val id: Long = 0,
    val title: String = "???",
    val original_title: String = "???",
    val genre: String = "???",
    val vote_average: Double = 0.0,
    val year_creation: String = "????",
    val runtime: Int = 0,
    val like: Boolean = false,
    val release_date: String = "???",
    val overview: String = "???",
    val budget: Long = 0,
    val revenue: Long = 0,
    val poster: String = "???"

) : Parcelable

fun getReleasedFilms() = listOf(
    Film(
        581726,
        "Бесконечность",
        "фантастика, боевик, триллер",
        vote_average = 7.5,
        year_creation = "2021"
    ),
    Film(1416, "Анатомия страсти", "драма", vote_average = 8.2, year_creation = "2005"),
    Film(
        566525, "Шан-Чи и легенда десяти колец",
        "боевик, приключения, фэнтези",
        vote_average = 8.0,
        year_creation = "2021",
        like = true
    ),
    Film(
        482373, "Не дыши 2", "триллер, ужасы", vote_average = 7.7, year_creation = "2021"
    ),
    Film(
        436969, "Отряд самоубийц: Миссия навылет",
        "боевик, приключения, фэнтези, комедия",
        vote_average = 8.0,
        year_creation = "2021"
    )
)

fun getUpcomingFilms() = listOf(
    Film(6, "Дюна", "приключения, драма, фантастика", year_creation = "2021"),
    Film(7, "Веном 2", "фантастика, боевик", year_creation = "2021"),
    Film(8, "Не время умирать", "приключения, боевик, триллер", year_creation = "2021"),
    Film(9, "Холодный расчет", "драма, триллер, боевик", year_creation = "2021"),
    Film(10, "Семейка Аддамс: Горящий тур", "мультфильм, комедия, семейный", year_creation = "2021"),
)