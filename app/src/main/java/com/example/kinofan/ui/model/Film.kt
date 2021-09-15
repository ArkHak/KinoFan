package com.example.kinofan.ui.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Film(
    val id: Long = 0,
    val title: String = "???",
    val originalTitle: String = "???",
    val genre: String = "???",
    val voteAverage: Double = 0.0,
    val yearСreation: String = "????",
    val runtime: Int = 0,
    val like: Boolean = false,
    val releaseDate: String = "???",
    val overview: String = "???",
    val budget: Long = 0,
    val revenue: Long = 0,
    val poster: String = "???"

) : Parcelable

fun getReleasedFilms() = listOf(
    Film(
        1,
        "Бесконечность",
        "фантастика, боевик, триллер",
        voteAverage = 7.5,
        yearСreation = "2021"
    ),
    Film(2, "Анатомия страсти", "драма", voteAverage = 8.2, yearСreation = "2005"),
    Film(
        3, "Шан-Чи и легенда десяти колец",
        "боевик, приключения, фэнтези",
        voteAverage = 8.0,
        yearСreation = "2021",
        like = true
    ),
    Film(
        4, "Не дыши 2", "триллер, ужасы", voteAverage = 7.7, yearСreation = "2021"
    ),
    Film(
        5, "Отряд самоубийц: Миссия навылет",
        "боевик, приключения, фэнтези, комедия",
        voteAverage = 8.0,
        yearСreation = "2021"
    )
)

fun getUpcomingFilms() = listOf(
    Film(6, "Дюна", "приключения, драма, фантастика", yearСreation = "2021"),
    Film(7, "Веном 2", "фантастика, боевик", yearСreation = "2021"),
    Film(8, "Не время умирать", "приключения, боевик, триллер", yearСreation = "2021"),
    Film(9, "Холодный расчет", "драма, триллер, боевик", yearСreation = "2021"),
    Film(10, "Семейка Аддамс: Горящий тур", "мультфильм, комедия, семейный", yearСreation = "2021"),
)